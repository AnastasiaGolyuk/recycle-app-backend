package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.BonusDto;
import com.bsuir.diploma.recycleappbackend.model.dto.BonusDto;
import com.bsuir.diploma.recycleappbackend.model.dto.BonusPrepareDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import com.bsuir.diploma.recycleappbackend.model.entity.Bonus;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import com.bsuir.diploma.recycleappbackend.model.mapper.BonusMapper;
import com.bsuir.diploma.recycleappbackend.repository.OrgNameRepository;
import com.bsuir.diploma.recycleappbackend.repository.BonusRepository;
import com.bsuir.diploma.recycleappbackend.repository.UserRepository;
import com.bsuir.diploma.recycleappbackend.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {
    private final BonusRepository bonusRepository;
    private final UserRepository userRepository;
    private final BonusMapper bonusMapper;

    @Transactional
    @Override
    public BonusDto saveBonus(BonusDto bonusDto) {
        BonusPrepareDto bonusPrepareDto = getBonusPrepareDto(bonusDto);
        Bonus entity = bonusMapper.toEntity(bonusDto);
        entity.setUser(bonusPrepareDto.getUser());
        Bonus savedBonus = bonusRepository.save(entity);
        return bonusMapper.toDto(savedBonus);
    }

    @Override
    public BonusDto findBonusById(Long id) {
        return bonusRepository.findById(id)
                .map(bonusMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
    }

    @Override
    public BonusDto findBonusByUserEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        return bonusRepository.findBonusByUserId(user.getId())
                .map(bonusMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));

    }


    @Transactional
    @Override
    public BonusDto updateBonus(BonusDto bonusDto) {
        bonusRepository.findById(bonusDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));

        BonusPrepareDto bonusPrepareDto = getBonusPrepareDto(bonusDto);
        Bonus entity = bonusMapper.toEntity(bonusDto);
        entity.setUser(bonusPrepareDto.getUser());
        Bonus updatedBonus = bonusRepository.save(entity);
        return bonusMapper.toDto(updatedBonus);
    }

    @Transactional
    @Override
    public void deleteBonusById(Long id) {
        bonusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        bonusRepository.deleteById(id);
    }


    private BonusPrepareDto getBonusPrepareDto(BonusDto bonusDto) {
        System.out.println(bonusDto.getUser().getId());
        User user = userRepository.findById(bonusDto.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        return new BonusPrepareDto(user);
    }
}

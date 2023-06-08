package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.*;
import com.bsuir.diploma.recycleappbackend.model.dto.UsersOfferDto;
import com.bsuir.diploma.recycleappbackend.model.entity.*;
import com.bsuir.diploma.recycleappbackend.model.mapper.UsersOfferMapper;
import com.bsuir.diploma.recycleappbackend.repository.OfferRepository;
import com.bsuir.diploma.recycleappbackend.repository.UsersOfferRepository;
import com.bsuir.diploma.recycleappbackend.repository.OrgRepresentativeRepository;
import com.bsuir.diploma.recycleappbackend.repository.UserRepository;
import com.bsuir.diploma.recycleappbackend.service.UsersOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersOfferServiceImpl implements UsersOfferService {

    private final UsersOfferRepository usersOfferRepository;
    private final UserRepository userRepository;

    private final OfferRepository offerRepository;
    private final UsersOfferMapper usersOfferMapper;

    @Transactional
    @Override
    public UsersOfferDto saveUsersOffer(UsersOfferDto usersOfferDto) {
        
        UsersOfferPrepareDto usersOfferPrepareDto = getUsersOfferPrepareDto(usersOfferDto);
        UsersOffer entity = usersOfferMapper.toEntity(usersOfferDto);
        entity.setUser(usersOfferPrepareDto.getUser());
        entity.setOffer(usersOfferPrepareDto.getOffer());
        UsersOffer savedUsersOffer = usersOfferRepository.save(entity);
        return usersOfferMapper.toDto(savedUsersOffer);
    }

    @Override
    public Page<UsersOfferDto> findUsersOfferByUserId(Pageable pageable, Long userId) {
        List<UsersOfferDto> usersOfferDtoList = usersOfferRepository.findAllByUserId(pageable,userId)
                .stream()
                .map(usersOfferMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(usersOfferDtoList, pageable, usersOfferRepository.countAllByUserId(userId));

    }

    @Override
    public UsersOfferDto findUsersOfferById(Long id) {
        return usersOfferRepository.findById(id)
                .map(usersOfferMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        
    }

    @Override
    public Page<UsersOfferDto> findAllUsersOffers(Pageable pageable) {
        List<UsersOfferDto> usersOfferDtoList = usersOfferRepository.findAll(pageable)
                .stream()
                .map(usersOfferMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(usersOfferDtoList, pageable, usersOfferRepository.count());
    }

    @Override
    public Long getUsersOffersCount() {
        return usersOfferRepository.count();
    }

    @Override
    public Long getUsersOffersCountByUserId(Long userId) {
        return usersOfferRepository.countAllByUserId(userId);
    }

    @Transactional
    @Override
    public UsersOfferDto updateUsersOffer(UsersOfferDto usersOfferDto) {
        usersOfferRepository.findById(usersOfferDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));

        UsersOfferPrepareDto usersOfferPrepareDto = getUsersOfferPrepareDto(usersOfferDto);
        UsersOffer entity = usersOfferMapper.toEntity(usersOfferDto);
        entity.setUser(usersOfferPrepareDto.getUser());
        entity.setOffer(usersOfferPrepareDto.getOffer());
        UsersOffer updatedUsersOffer = usersOfferRepository.save(entity);
        return usersOfferMapper.toDto(updatedUsersOffer);
    }

    @Transactional
    @Override
    public void deleteUsersOfferById(Long id) {
        usersOfferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        usersOfferRepository.deleteById(id);
    }

    private UsersOfferPrepareDto getUsersOfferPrepareDto(UsersOfferDto usersOfferDto) {
        User user = userRepository.findById(usersOfferDto.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        Offer offer = offerRepository.findById(usersOfferDto.getOffer().getId())
                .orElseThrow(() -> new EntityNotFoundException("BUSINESS_OWNER_NOT_FOUND_ERROR"));

        return new UsersOfferPrepareDto(user, offer);
    }
}

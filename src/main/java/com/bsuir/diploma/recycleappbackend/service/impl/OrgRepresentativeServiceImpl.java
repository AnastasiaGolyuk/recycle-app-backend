package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativeDto;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativePrepareDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import com.bsuir.diploma.recycleappbackend.model.mapper.OrgRepresentativeMapper;
import com.bsuir.diploma.recycleappbackend.repository.OrgNameRepository;
import com.bsuir.diploma.recycleappbackend.repository.OrgRepresentativeRepository;
import com.bsuir.diploma.recycleappbackend.repository.UserRepository;
import com.bsuir.diploma.recycleappbackend.service.OrgRepresentativeService;
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
public class OrgRepresentativeServiceImpl implements OrgRepresentativeService {

    private final OrgRepresentativeRepository orgRepresentativeRepository;
    private final UserRepository userRepository;

    private final OrgNameRepository orgNameRepository;
    private final OrgRepresentativeMapper orgRepresentativeMapper;

    @Transactional
    @Override
    public OrgRepresentativeDto saveOrgRepresentative(OrgRepresentativeDto orgRepresentativeDto) {
        OrgRepresentativePrepareDto orgRepresentativePrepareDto = getOrgRepresentativePrepareDto(orgRepresentativeDto);
        OrgRepresentative entity = orgRepresentativeMapper.toEntity(orgRepresentativeDto);
        entity.setUser(orgRepresentativePrepareDto.getUser());
        entity.setOrgName(orgRepresentativePrepareDto.getOrgName());
        OrgRepresentative savedOrgRepresentative = orgRepresentativeRepository.save(entity);
        return orgRepresentativeMapper.toDto(savedOrgRepresentative);
    }

    @Override
    public OrgRepresentativeDto findOrgRepresentativeById(Long id) {
        return orgRepresentativeRepository.findById(id)
                .map(orgRepresentativeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
    }

    @Override
    public OrgRepresentativeDto findOrgRepresentativeByEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        return orgRepresentativeRepository.findOrgRepresentativeByUserId(user.getId())
                .map(orgRepresentativeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));

    }

    @Override
    public OrgRepresentativeDto findOrgRepresentativeByOrgName(String orgName) {
        OrgName orgObj = orgNameRepository.findOrgByName(orgName)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        return orgRepresentativeRepository.findOrgRepresentativeByOrgNameId(orgObj.getId())
                .map(orgRepresentativeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));

    }

    @Override
    public Page<OrgRepresentativeDto> findAllOrgRepresentatives(Pageable pageable) {
        List<OrgRepresentativeDto> orgRepresentativeDtoList = orgRepresentativeRepository.findAll(pageable)
                .stream()
                .map(orgRepresentativeMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(orgRepresentativeDtoList, pageable, orgRepresentativeRepository.count());
    }

    @Override
    public Long getOrgRepresentativesCount() {
        return orgRepresentativeRepository.count();
    }

    @Transactional
    @Override
    public OrgRepresentativeDto updateOrgRepresentative(OrgRepresentativeDto orgRepresentativeDto) {
        orgRepresentativeRepository.findById(orgRepresentativeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));

        OrgRepresentativePrepareDto orgRepresentativePrepareDto = getOrgRepresentativePrepareDto(orgRepresentativeDto);
        OrgRepresentative entity = orgRepresentativeMapper.toEntity(orgRepresentativeDto);
        entity.setUser(orgRepresentativePrepareDto.getUser());
        entity.setOrgName(orgRepresentativePrepareDto.getOrgName());
        OrgRepresentative updatedOrgRepresentative = orgRepresentativeRepository.save(entity);
        return orgRepresentativeMapper.toDto(updatedOrgRepresentative);
    }

    @Transactional
    @Override
    public void deleteOrgRepresentativeById(Long id) {
        orgRepresentativeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        orgRepresentativeRepository.deleteById(id);
    }


    private OrgRepresentativePrepareDto getOrgRepresentativePrepareDto(OrgRepresentativeDto orgRepresentativeDto) {
        System.out.println(orgRepresentativeDto.getUser().getId());
        User user = userRepository.findById(orgRepresentativeDto.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        OrgName orgName = orgNameRepository.findById(orgRepresentativeDto.getOrgName().getId())
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        return new OrgRepresentativePrepareDto(user,orgName);
    }
}

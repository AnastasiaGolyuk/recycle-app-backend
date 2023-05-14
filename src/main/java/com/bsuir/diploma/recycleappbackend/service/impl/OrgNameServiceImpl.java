package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgNameDto;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgNameDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import com.bsuir.diploma.recycleappbackend.model.mapper.OrgNameMapper;
import com.bsuir.diploma.recycleappbackend.repository.OrgNameRepository;
import com.bsuir.diploma.recycleappbackend.service.OrgNameService;
import com.bsuir.diploma.recycleappbackend.service.OrgNameService;
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
public class OrgNameServiceImpl implements OrgNameService {

    private final OrgNameRepository orgNameRepository;
    private final OrgNameMapper orgNameMapper;

    @Transactional
    @Override
    public OrgNameDto saveOrgName(OrgNameDto orgNameDto) {
        OrgName entity = orgNameMapper.toEntity(orgNameDto);
        OrgName savedOrgName = orgNameRepository.save(entity);
        return orgNameMapper.toDto(savedOrgName);
    }

    @Override
    public OrgNameDto findOrgByName(String name) {
        return orgNameRepository.findOrgByName(name)
                .map(orgNameMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("OrgName not found with name: " + name));
    }

    @Override
    public boolean existsByName(String name) {
        return orgNameRepository.existsByName(name);
    }

    @Override
    public OrgNameDto findOrgNameById(Long id) {
        return orgNameRepository.findById(id)
                .map(orgNameMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("OrgName not found with id: " + id));
    }

    @Override
    public Page<OrgNameDto> findAllOrgNames(Pageable pageable) {
        List<OrgNameDto> orgNameDtoList = orgNameRepository.findAll(pageable)
                .stream()
                .map(orgNameMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(orgNameDtoList, pageable, orgNameRepository.count());
    }

    @Override
    public Long getOrgNamesCount() {
        return orgNameRepository.count();
    }

    @Transactional
    @Override
    public OrgNameDto updateOrgName(OrgNameDto orgNameDto) {
        orgNameRepository.findById(orgNameDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("OrgName not found with id: " + orgNameDto.getId()));
        OrgName entity = orgNameMapper.toEntity(orgNameDto);
        OrgName updatedOrgName = orgNameRepository.save(entity);
        return orgNameMapper.toDto(updatedOrgName);
    }

    @Transactional
    @Override
    public void deleteOrgNameById(Long id) {
        orgNameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrgName not found with id: " + id));
        orgNameRepository.deleteById(id);
    }

}

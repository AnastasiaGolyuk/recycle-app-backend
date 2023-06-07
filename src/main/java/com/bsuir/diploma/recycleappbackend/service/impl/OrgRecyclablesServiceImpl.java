package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgRecyclablesDto;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgRecyclablesPrepareDto;
import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgRecyclables;
import com.bsuir.diploma.recycleappbackend.model.entity.RecycleSymbolType;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import com.bsuir.diploma.recycleappbackend.model.mapper.OrgRecyclablesMapper;
import com.bsuir.diploma.recycleappbackend.repository.OrgNameRepository;
import com.bsuir.diploma.recycleappbackend.repository.OrgRecyclablesRepository;
import com.bsuir.diploma.recycleappbackend.repository.UserRepository;
import com.bsuir.diploma.recycleappbackend.service.OrgRecyclablesService;
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
public class OrgRecyclablesServiceImpl implements OrgRecyclablesService {

    private final OrgRecyclablesRepository orgRecyclablesRepository;
    private final OrgNameRepository orgNameRepository;
    private final OrgRecyclablesMapper orgRecyclablesMapper;

    @Transactional
    @Override
    public OrgRecyclablesDto saveOrgRecyclables(OrgRecyclablesDto orgRecyclablesDto) {
        OrgRecyclablesPrepareDto orgRecyclablesPrepareDto = getOrgRecyclablesPrepareDto(orgRecyclablesDto);
        OrgRecyclables entity = orgRecyclablesMapper.toEntity(orgRecyclablesDto);
        entity.setOrgName(orgRecyclablesPrepareDto.getOrgName());
        OrgRecyclables savedOrgRecyclables = orgRecyclablesRepository.save(entity);
        return orgRecyclablesMapper.toDto(savedOrgRecyclables);
    }

    @Override
    public OrgRecyclablesDto findOrgRecyclablesById(Long id) {
        return orgRecyclablesRepository.findById(id)
                .map(orgRecyclablesMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
    }

    @Override
    public OrgRecyclablesDto findOrgRecyclablesByType(String type) {
        return orgRecyclablesRepository.findOrgRecyclablesByType(type)
                .map(orgRecyclablesMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));

    }

    @Override
    public Page<OrgRecyclablesDto> findAllOrgRecyclables(Pageable pageable) {
        List<OrgRecyclablesDto> orgRecyclablesDtoList = orgRecyclablesRepository.findAll(pageable)
                .stream()
                .map(orgRecyclablesMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(orgRecyclablesDtoList, pageable, orgRecyclablesRepository.count());
    }

    @Override
    public Page<OrgRecyclablesDto> findAllOrgRecyclablesByOrgName(String orgName, Pageable pageable) {
        OrgName orgNameObj = orgNameRepository.findOrgByName(orgName)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        List<OrgRecyclablesDto> orgRecyclablesDtoList = orgRecyclablesRepository.findOrgRecyclablesByOrgName_Name(orgNameObj.getName(),pageable)
                .stream()
                .map(orgRecyclablesMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(orgRecyclablesDtoList, pageable, orgRecyclablesRepository.count());
    }

    @Override
    public Long getOrgRecyclablesCount() {
        return orgRecyclablesRepository.count();
    }

    @Override
    public Long getOrgRecyclablesCountByOrgName(String name) {
        return orgRecyclablesRepository.countAllByOrgName_Name(name);
    }


    @Transactional
    @Override
    public OrgRecyclablesDto updateOrgRecyclables(OrgRecyclablesDto orgRecyclablesDto) {
        orgRecyclablesRepository.findById(orgRecyclablesDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));

        OrgRecyclablesPrepareDto orgRecyclablesPrepareDto = getOrgRecyclablesPrepareDto(orgRecyclablesDto);
        OrgRecyclables entity = orgRecyclablesMapper.toEntity(orgRecyclablesDto);
        entity.setOrgName(orgRecyclablesPrepareDto.getOrgName());
        OrgRecyclables updatedOrgRecyclables = orgRecyclablesRepository.save(entity);
        return orgRecyclablesMapper.toDto(updatedOrgRecyclables);
    }

    @Transactional
    @Override
    public void deleteOrgRecyclablesById(Long id) {
        orgRecyclablesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        orgRecyclablesRepository.deleteById(id);
    }


    private OrgRecyclablesPrepareDto getOrgRecyclablesPrepareDto(OrgRecyclablesDto orgRecyclablesDto) {
        OrgName orgName = orgNameRepository.findById(orgRecyclablesDto.getOrgName().getId())
                .orElseThrow(() -> new EntityNotFoundException("ORG_NOT_FOUND_ERROR"));
        return new OrgRecyclablesPrepareDto(orgName);
    }
}

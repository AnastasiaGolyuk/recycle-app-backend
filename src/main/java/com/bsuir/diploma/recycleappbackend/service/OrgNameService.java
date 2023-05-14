package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgNameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrgNameService {

    OrgNameDto saveOrgName(OrgNameDto orgNameDto);

    OrgNameDto findOrgByName(String name);

    boolean existsByName(String name);

    OrgNameDto findOrgNameById(Long id);

    Page<OrgNameDto> findAllOrgNames(Pageable pageable);

    Long getOrgNamesCount();

    OrgNameDto updateOrgName(OrgNameDto orgNameDto);

    void deleteOrgNameById(Long id);
}

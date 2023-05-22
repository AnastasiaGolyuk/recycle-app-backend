package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRecyclablesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrgRecyclablesService {

    OrgRecyclablesDto saveOrgRecyclables(OrgRecyclablesDto orgRecyclablesDto);

    OrgRecyclablesDto findOrgRecyclablesById(Long id);
    OrgRecyclablesDto findOrgRecyclablesByType(String type);


    Page<OrgRecyclablesDto> findAllOrgRecyclables(Pageable pageable);

    Page<OrgRecyclablesDto> findAllOrgRecyclablesByOrgName(String orgName, Pageable pageable);

    Long getOrgRecyclablesCount();

    OrgRecyclablesDto updateOrgRecyclables(OrgRecyclablesDto orgRecyclablesDto);

    void deleteOrgRecyclablesById(Long id);
}

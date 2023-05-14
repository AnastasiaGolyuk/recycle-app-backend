package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrgRepresentativeService {
    OrgRepresentativeDto saveOrgRepresentative(OrgRepresentativeDto orgRepresentativeDto);

    OrgRepresentativeDto findOrgRepresentativeById(Long id);

    OrgRepresentativeDto findOrgRepresentativeByEmail(String email);

    OrgRepresentativeDto findOrgRepresentativeByOrgName(String orgName);

    Page<OrgRepresentativeDto> findAllOrgRepresentatives(Pageable pageable);

    Long getOrgRepresentativesCount();


    OrgRepresentativeDto updateOrgRepresentative(OrgRepresentativeDto orgRepresentativeDto);

    void deleteOrgRepresentativeById(Long id);
}

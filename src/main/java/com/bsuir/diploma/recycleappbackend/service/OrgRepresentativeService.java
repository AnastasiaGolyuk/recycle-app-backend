package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrgRepresentativeService {
    OrgRepresentativeDto saveOrgRepresentative(OrgRepresentativeDto orgRepresentativeDto);

    OrgRepresentativeDto findOrgRepresentativeById(Long id);

    OrgRepresentativeDto findOrgRepresentativeByEmail(String email);

    OrgRepresentativeDto findOrgRepresentativeByOrgName(String orgName);

    Page<OrgRepresentativeDto> findAllOrgRepresentatives(Pageable pageable);

    List<OrgRepresentativeDto> findAllOrgRepresentativeList();

    Long getOrgRepresentativesCount();


    OrgRepresentativeDto updateOrgRepresentative(OrgRepresentativeDto orgRepresentativeDto);

    void deleteOrgRepresentativeById(Long id);
}

package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgNameDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrgNameMapper {
    OrgNameDto toDto(OrgName orgName);

    OrgName toEntity(OrgNameDto orgNameDto);
}


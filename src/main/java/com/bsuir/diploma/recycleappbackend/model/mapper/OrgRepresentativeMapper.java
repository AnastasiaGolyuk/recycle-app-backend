package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativeDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, OrgNameMapper.class})
public interface OrgRepresentativeMapper {

    OrgRepresentativeDto toDto(OrgRepresentative orgRepresentative);

    OrgRepresentative toEntity(OrgRepresentativeDto orgRepresentativeDto);
}

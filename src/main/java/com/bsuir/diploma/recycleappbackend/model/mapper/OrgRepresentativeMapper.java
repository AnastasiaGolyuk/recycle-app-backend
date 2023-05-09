package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativeDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface OrgRepresentativeMapper {

    @Mapping(source = "user", target = "userDto")
    OrgRepresentativeDto toDto(OrgRepresentative orgRepresentative);

    @Mapping(source = "userDto", target = "user")
    OrgRepresentative toEntity(OrgRepresentativeDto orgRepresentativeDto);
}

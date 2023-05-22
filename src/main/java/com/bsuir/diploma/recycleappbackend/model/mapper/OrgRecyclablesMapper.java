package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRecyclablesDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgRecyclables;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrgNameMapper.class})
public interface OrgRecyclablesMapper {
    OrgRecyclablesDto toDto(OrgRecyclables orgRecyclables);

    OrgRecyclables toEntity(OrgRecyclablesDto orgRecyclablesDto);
}

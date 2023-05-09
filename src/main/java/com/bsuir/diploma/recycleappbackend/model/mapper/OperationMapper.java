package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.OperationDto;
import com.bsuir.diploma.recycleappbackend.model.entity.Operation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, OrgRepresentativeMapper.class})
public interface OperationMapper {
    OperationDto toDto(Operation operation);

    Operation toEntity(OperationDto operationDto);
}

package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolTypeDto;
import com.bsuir.diploma.recycleappbackend.model.entity.RecycleSymbolType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecycleSymbolTypeMapper {

    RecycleSymbolTypeDto toDto(RecycleSymbolType recycleSymbolType);

    RecycleSymbolType toEntity(RecycleSymbolTypeDto recycleSymbolTypeDto);
}

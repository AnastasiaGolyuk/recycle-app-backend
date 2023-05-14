package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolDto;
import com.bsuir.diploma.recycleappbackend.model.entity.RecycleSymbol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecycleSymbolMapper {

    RecycleSymbolDto toDto(RecycleSymbol recycleSymbol);

    RecycleSymbol toEntity(RecycleSymbolDto recycleSymbolDto);
}

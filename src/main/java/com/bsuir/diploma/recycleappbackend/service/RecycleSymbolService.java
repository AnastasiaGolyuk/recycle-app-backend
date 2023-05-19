package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativeDto;
import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecycleSymbolService {

    RecycleSymbolDto findRecycleSymbolByName(String name);

    Page<RecycleSymbolDto> findAllRecycleSymbols(Pageable pageable);

    Page<RecycleSymbolDto> findRecycleSymbolsByTypeName(String typeName, Pageable pageable);

    Long getRecycleSymbolsCount();
}

package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecycleSymbolTypeService {

    RecycleSymbolTypeDto findRecycleSymbolTypeByName(String name);

    Page<RecycleSymbolTypeDto> findAllRecycleSymbolTypes(Pageable pageable);

    Long getRecycleSymbolTypesCount();
}

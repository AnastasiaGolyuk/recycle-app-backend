package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolTypeDto;
import com.bsuir.diploma.recycleappbackend.model.mapper.RecycleSymbolTypeMapper;
import com.bsuir.diploma.recycleappbackend.repository.RecycleSymbolTypeRepository;
import com.bsuir.diploma.recycleappbackend.service.RecycleSymbolTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecycleSymbolTypeServiceImpl implements RecycleSymbolTypeService {

    private final RecycleSymbolTypeRepository recycleSymbolTypeRepository;
    private final RecycleSymbolTypeMapper recycleSymbolTypeMapper;


    @Override
    public RecycleSymbolTypeDto findRecycleSymbolTypeByName(String name) {
        return recycleSymbolTypeRepository.findRecycleSymbolTypeByName(name)
                .map(recycleSymbolTypeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("RecycleSymbolType not found with name: " + name));
    }

    @Override
    public Page<RecycleSymbolTypeDto> findAllRecycleSymbolTypes(Pageable pageable) {
        List<RecycleSymbolTypeDto> recycleSymbolTypeDtoList = recycleSymbolTypeRepository.findAll(pageable)
                .stream()
                .map(recycleSymbolTypeMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(recycleSymbolTypeDtoList, pageable, recycleSymbolTypeRepository.count());
    }

    @Override
    public Long getRecycleSymbolTypesCount() {
        return recycleSymbolTypeRepository.count();
    }
}



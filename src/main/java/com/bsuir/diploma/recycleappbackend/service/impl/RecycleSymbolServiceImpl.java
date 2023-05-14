package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolDto;
import com.bsuir.diploma.recycleappbackend.model.mapper.RecycleSymbolMapper;
import com.bsuir.diploma.recycleappbackend.repository.RecycleSymbolRepository;
import com.bsuir.diploma.recycleappbackend.service.RecycleSymbolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecycleSymbolServiceImpl implements RecycleSymbolService {

    private final RecycleSymbolRepository recycleSymbolRepository;
    private final RecycleSymbolMapper recycleSymbolMapper;


    @Override
    public RecycleSymbolDto findRecycleSymbolByName(String name) {
        return recycleSymbolRepository.findRecycleSymbolByName(name)
                .map(recycleSymbolMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("RecycleSymbol not found with name: " + name));
    }

    @Override
    public Page<RecycleSymbolDto> findAllRecycleSymbols(Pageable pageable) {
        List<RecycleSymbolDto> recycleSymbolDtoList = recycleSymbolRepository.findAll(pageable)
                .stream()
                .map(recycleSymbolMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(recycleSymbolDtoList, pageable, recycleSymbolRepository.count());
    }

    @Override
    public Long getRecycleSymbolsCount() {
        return recycleSymbolRepository.count();
    }
}


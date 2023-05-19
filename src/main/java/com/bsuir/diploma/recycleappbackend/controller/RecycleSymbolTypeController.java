package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolTypeDto;
import com.bsuir.diploma.recycleappbackend.service.RecycleSymbolTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recycle-symbol-types")
public class RecycleSymbolTypeController {

    private final RecycleSymbolTypeService recycleSymbolTypeService;


    @GetMapping
    public List<RecycleSymbolTypeDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                          @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<RecycleSymbolTypeDto> recycleSymbolTypePage = recycleSymbolTypeService.findAllRecycleSymbolTypes(PageRequest.of(page, size));
        return new ArrayList<>(recycleSymbolTypePage.getContent());
    }


    @GetMapping("/name/{name}")
    public RecycleSymbolTypeDto findRecycleSymbolTypeByName(@PathVariable String name) {
        return recycleSymbolTypeService.findRecycleSymbolTypeByName(name);
    }

    @GetMapping("/count")
    public Long getRecycleSymbolTypesCount() {
        return recycleSymbolTypeService.getRecycleSymbolTypesCount();
    }
}


package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolDto;
import com.bsuir.diploma.recycleappbackend.service.RecycleSymbolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recycle-symbols")
public class RecycleSymbolController {

    private final RecycleSymbolService recycleSymbolService;


    @GetMapping
    public List<RecycleSymbolDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<RecycleSymbolDto> recycleSymbolPage = recycleSymbolService.findAllRecycleSymbols(PageRequest.of(page, size));
        return new ArrayList<>(recycleSymbolPage.getContent());
    }

    @GetMapping("/name/{name}")
    public List<RecycleSymbolDto> findRecycleSymbolsByTypeName(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                          @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                               @PathVariable String name) {
        Page<RecycleSymbolDto> recycleSymbolPage = recycleSymbolService.findRecycleSymbolsByTypeName(name,PageRequest.of(page, size));
        return new ArrayList<>(recycleSymbolPage.getContent());
    }

    @GetMapping("/keyword/{keyword}")
    public List<RecycleSymbolDto> findRecycleSymbolsByKeyword(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                               @PathVariable String keyword) {
        Page<RecycleSymbolDto> recycleSymbolPage = recycleSymbolService.findRecycleSymbolsByKeyword(keyword,PageRequest.of(page, size));
        return new ArrayList<>(recycleSymbolPage.getContent());
    }

    @GetMapping("/count")
    public Long getRecycleSymbolsCount() {
        return recycleSymbolService.getRecycleSymbolsCount();
    }

    @GetMapping("/count-keyword/{keyword}")
    public Long getRecycleSymbolsCountByKeyword(@PathVariable String keyword) {
        return recycleSymbolService.getRecycleSymbolsCountByKeyword(keyword);
    }

    @GetMapping("/count-name/{name}")
    public Long getRecycleSymbolsCountByTypeName(@PathVariable String name) {
        return recycleSymbolService.getRecycleSymbolsCountByTypeName(name);
    }
}


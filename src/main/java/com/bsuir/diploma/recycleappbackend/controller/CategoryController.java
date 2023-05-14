package com.bsuir.diploma.recycleappbackend.controller;


import com.bsuir.diploma.recycleappbackend.model.dto.CategoryDto;
import com.bsuir.diploma.recycleappbackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    

    @GetMapping
    public List<CategoryDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                 @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<CategoryDto> categoryPage = categoryService.findAllCategories(PageRequest.of(page, size));
        return new ArrayList<>(categoryPage.getContent());
    }
    

    @GetMapping("/name/{name}")
    public CategoryDto findCategoryByName(@PathVariable String name) {
        return categoryService.findCategoryByName(name);
    }

    @GetMapping("/count")
    public Long getCategoriesCount() {
        return categoryService.getCategoriesCount();
    }
}

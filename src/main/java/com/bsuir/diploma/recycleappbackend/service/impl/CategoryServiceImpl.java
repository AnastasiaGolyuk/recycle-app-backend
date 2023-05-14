package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.CategoryDto;
import com.bsuir.diploma.recycleappbackend.model.dto.CategoryDto;
import com.bsuir.diploma.recycleappbackend.model.entity.Category;
import com.bsuir.diploma.recycleappbackend.model.mapper.CategoryMapper;
import com.bsuir.diploma.recycleappbackend.repository.CategoryRepository;
import com.bsuir.diploma.recycleappbackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryDto findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with name: " + name));
    }

    @Override
    public Page<CategoryDto> findAllCategories(Pageable pageable) {
        List<CategoryDto> categoryDtoList = categoryRepository.findAll(pageable)
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(categoryDtoList, pageable, categoryRepository.count());
    }

    @Override
    public Long getCategoriesCount() {
        return categoryRepository.count();
    }
}

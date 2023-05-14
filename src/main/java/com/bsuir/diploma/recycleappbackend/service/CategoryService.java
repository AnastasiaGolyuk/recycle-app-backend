package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryDto findCategoryByName(String name);

    Page<CategoryDto> findAllCategories(Pageable pageable);

    Long getCategoriesCount();
}

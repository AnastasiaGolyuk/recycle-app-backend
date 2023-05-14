package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.CategoryDto;
import com.bsuir.diploma.recycleappbackend.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);

}

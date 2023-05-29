package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.NewsDto;
import com.bsuir.diploma.recycleappbackend.model.entity.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsDto toDto(News news);

    News toEntity(NewsDto newsDto);
}

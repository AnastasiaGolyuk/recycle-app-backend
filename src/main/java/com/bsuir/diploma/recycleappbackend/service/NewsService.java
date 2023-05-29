package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.NewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface NewsService {

    NewsDto saveNews(NewsDto newsDto);

    NewsDto findNewsById(Long id);

    Page<NewsDto> findAllNews(Pageable pageable);

    Page<NewsDto> findAllNewsByDateAfter(Pageable pageable, LocalDate date);

    NewsDto updateNews(NewsDto newsDto);

    void deleteNewsById(Long id);

    Long getNewsCount();
}

package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.NewsDto;
import com.bsuir.diploma.recycleappbackend.model.entity.News;
import com.bsuir.diploma.recycleappbackend.model.mapper.NewsMapper;
import com.bsuir.diploma.recycleappbackend.repository.NewsRepository;
import com.bsuir.diploma.recycleappbackend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Transactional
    @Override
    public NewsDto saveNews(NewsDto newsDto) {
        News entity = newsMapper.toEntity(newsDto);
        News savedNews = newsRepository.save(entity);
        return newsMapper.toDto(savedNews);
    }


    @Override
    public NewsDto findNewsById(Long id) {
        return newsRepository.findById(id)
                .map(newsMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("news_NOT_FOUND_ERROR"));
    }

    @Override
    public Page<NewsDto> findAllNews(Pageable pageable) {
        List<NewsDto> newsDtoList = newsRepository.findAll(pageable)
                .stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(newsDtoList, pageable, newsRepository.count());
    }

    @Override
    public Page<NewsDto> findAllNewsByDateAfter(Pageable pageable, LocalDate date) {
        List<NewsDto> newsDtoList = newsRepository.findAllByDateAfter(pageable, date)
                .stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(newsDtoList, pageable, newsRepository.countAllByDateAfter(date));

    }

    @Override
    public Page<NewsDto> findAllByKeyWord(Pageable pageable, String keyword) {
        List<NewsDto> newsDtoList = newsRepository.findAllByTitleContainsIgnoreCaseOrSubjectContainsIgnoreCase(pageable, keyword)
                .stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(newsDtoList, pageable, newsRepository.countAllBySubjectContainsIgnoreCaseOrTitleContainsIgnoreCase(keyword));
    }

    @Override
    public Page<NewsDto> findAllNewsBySource(Pageable pageable, String source) {
        List<NewsDto> newsDtoList = newsRepository.findAllBySource(pageable,source)
                .stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(newsDtoList, pageable, newsRepository.countAllBySource(source));
    }


    @Transactional
    @Override
    public NewsDto updateNews(NewsDto newsDto) {
        newsRepository.findById(newsDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        News entity = newsMapper.toEntity(newsDto);
        News updatedNews = newsRepository.save(entity);
        return newsMapper.toDto(updatedNews);
    }

    @Transactional
    @Override
    public void deleteNewsById(Long id) {
        newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        newsRepository.deleteById(id);
    }

    @Override
    public Long getNewsCount() {
        return newsRepository.count();
    }

    @Override
    public Long getNewsCountByKeyWord(String keyword) {
        return newsRepository.countAllBySubjectContainsIgnoreCaseOrTitleContainsIgnoreCase(keyword);
    }

    @Override
    public Long getNewsCountBySource(String source) {
        return newsRepository.countAllBySource(source);
    }

    @Override
    public Long getNewsCountByDateAfter(LocalDate date) {
        return newsRepository.countAllByDateAfter(date);
    }

}

package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.NewsDto;
import com.bsuir.diploma.recycleappbackend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    public NewsDto save(@RequestBody NewsDto newsDto) {
        return newsService.saveNews(newsDto);
    }

    @GetMapping
    public List<NewsDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                 @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<NewsDto> newsPage = newsService.findAllNews(PageRequest.of(page, size));
        System.out.println(page);
        return new ArrayList<>(newsPage.getContent());
    }

    @GetMapping("/{id}")
    public NewsDto findById(@PathVariable Long id) {
        return newsService.findNewsById(id);
    }

    @GetMapping("/date/{date}")
    public List<NewsDto> findNewsByDateAfter(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                             @RequestParam(name = "size", defaultValue = "10") Integer size,
                                             @PathVariable LocalDate date) {
        Page<NewsDto> newsPage = newsService.findAllNewsByDateAfter(PageRequest.of(page, size),date);
        return new ArrayList<>(newsPage.getContent());
    }


    @PatchMapping
    public NewsDto update(@RequestBody NewsDto newsDto) {
        return newsService.updateNews(newsDto);
    }

    @GetMapping("/count")
    public Long getNewsCount() {
        return newsService.getNewsCount();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        newsService.deleteNewsById(id);
    }
}

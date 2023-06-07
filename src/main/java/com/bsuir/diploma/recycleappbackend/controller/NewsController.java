package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.NewsDto;
import com.bsuir.diploma.recycleappbackend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
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
        return new ArrayList<>(newsPage.getContent());
    }

    @GetMapping("/{id}")
    public NewsDto findById(@PathVariable Long id) {
        return newsService.findNewsById(id);
    }

    @GetMapping("/date/{date}")
    public List<NewsDto> findNewsByDateAfter(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                             @RequestParam(name = "size", defaultValue = "10") Integer size,
                                              @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Page<NewsDto> newsPage = newsService.findAllNewsByDateAfter(PageRequest.of(page, size),date);
        return new ArrayList<>(newsPage.getContent());
    }

    @GetMapping("/source/{source}")
    public List<NewsDto> findNewsBySource(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                             @RequestParam(name = "size", defaultValue = "10") Integer size,
                                             @PathVariable String source) {
        Page<NewsDto> newsPage = newsService.findAllNewsBySource(PageRequest.of(page, size),source);
        return new ArrayList<>(newsPage.getContent());
    }

    @GetMapping("/keyword/{keyword}")
    public List<NewsDto> findNewsByKeyword(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                          @RequestParam(name = "size", defaultValue = "10") Integer size,
                                          @PathVariable String keyword) {
        Page<NewsDto> newsPage = newsService.findAllByKeyWord(PageRequest.of(page, size),keyword);
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

    @GetMapping("/count-source")
    public Long getNewsCountBySource(@RequestParam(name = "source") String source) {
        return newsService.getNewsCountBySource(source);
    }

    @GetMapping("/count-date")
    public Long getNewsCountByDate(@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return newsService.getNewsCountByDateAfter(date);
    }

    @GetMapping("/count-keyword")
    public Long getNewsCountByKeyword(@RequestParam(name = "keyword") String keyword) {
        return newsService.getNewsCountByKeyWord(keyword);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        newsService.deleteNewsById(id);
    }
}

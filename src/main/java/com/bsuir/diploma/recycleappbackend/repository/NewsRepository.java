package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findAllByDateAfter(Pageable pageable, LocalDate date);

    Page<News> findAllBySource(Pageable pageable, String source);

    @Query("select news from News news where lower(news.title) like lower(concat('%', :keyWord, '%')) " +
            "or lower(news.subject) like lower(concat('%', :keyWord, '%'))")
    Page<News> findAllByTitleContainsIgnoreCaseOrSubjectContainsIgnoreCase
            (Pageable pageable, String keyWord);

    Long countAllByDateAfter(LocalDate date);

    Long countAllBySource(String source);

    @Query("select count(news) from News news where lower(news.title) like lower(concat('%', :keyWord, '%')) " +
            "or lower(news.subject) like lower(concat('%', :keyWord, '%'))")
    Long countAllBySubjectContainsIgnoreCaseOrTitleContainsIgnoreCase(String keyWord);
}

package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.dto.NewsDto;
import com.bsuir.diploma.recycleappbackend.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findAllByDateAfter(Pageable pageable, LocalDate date);

    Page<News> findAllBySource(Pageable pageable, String source);

    Long countAllBySource(String source);
}

package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.News;
import com.bsuir.diploma.recycleappbackend.model.entity.RecycleSymbol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.DoubleStream;
@Repository
public interface RecycleSymbolRepository extends JpaRepository<RecycleSymbol, Long> {

    Page<RecycleSymbol> findRecycleSymbolsByRecycleSymbolTypeName(String name, Pageable pageable);

    Optional<RecycleSymbol> findRecycleSymbolByName(String name);

    @Query("select recyclables from RecycleSymbol recyclables where lower(recyclables.name) like lower(concat('%', :keyWord, '%')) " +
            "or lower(recyclables.description) like lower(concat('%', :keyWord, '%'))")
    Page<RecycleSymbol> findAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase
            (Pageable pageable, String keyWord);

    @Query("select count(recyclables) from RecycleSymbol recyclables where lower(recyclables.name) like lower(concat('%', :keyWord, '%')) " +
            "or lower(recyclables.description) like lower(concat('%', :keyWord, '%'))")
    Long countAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String keyWord);

    Long countAllByRecycleSymbolTypeName(String name);
}

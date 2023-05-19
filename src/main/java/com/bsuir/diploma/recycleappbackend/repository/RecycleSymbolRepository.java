package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.RecycleSymbol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.DoubleStream;

public interface RecycleSymbolRepository extends JpaRepository<RecycleSymbol, Long> {

    Page<RecycleSymbol> findRecycleSymbolsByRecycleSymbolTypeName(String name, Pageable pageable);

    Optional<RecycleSymbol> findRecycleSymbolByName(String name);
}

package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.RecycleSymbol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecycleSymbolRepository extends JpaRepository<RecycleSymbol, Long> {

    Optional<RecycleSymbol> findRecycleSymbolByName(String name);
}

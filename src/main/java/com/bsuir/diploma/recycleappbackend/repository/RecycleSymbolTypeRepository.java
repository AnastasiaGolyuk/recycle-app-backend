package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.RecycleSymbolType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecycleSymbolTypeRepository extends JpaRepository<RecycleSymbolType, Long> {

    Optional<RecycleSymbolType> findRecycleSymbolTypeByName(String name);
}

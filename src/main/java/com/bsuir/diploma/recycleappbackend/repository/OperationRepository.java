package com.bsuir.diploma.recycleappbackend.repository;


import com.bsuir.diploma.recycleappbackend.model.entity.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    boolean existsByUserId(Long userId);

    Page<Operation> findAllByUserId(Long id, Pageable pageable);

    Page<Operation> findAllByOrgRepresentativeId(Long id, Pageable pageable);

    boolean existsByOrgRepresentativeId(Long id);
}

package com.bsuir.diploma.recycleappbackend.repository;


import com.bsuir.diploma.recycleappbackend.model.entity.News;
import com.bsuir.diploma.recycleappbackend.model.entity.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    boolean existsByUserId(Long userId);
    @Query("select operations from Operation operations where operations.user.id = :id order by operations.dateTime desc")
    Page<Operation> findAllByUserId(Long id, Pageable pageable);

    @Query("select operations from Operation operations where operations.dateTime >= :date and operations.orgRepresentative.id = :orgRepId order by operations.dateTime desc")
    Page<Operation> findAllByOrgRepresentativeIdAndDateTimeAfter
            (Pageable pageable, LocalDateTime date, Long orgRepId);

    @Query("select operations from Operation operations where operations.dateTime >= :date and operations.user.id = :userId order by operations.dateTime desc")
    Page<Operation> findAllByUserIdAndDateTimeAfter
            (Pageable pageable, LocalDateTime date, Long userId);

    @Query("select operations from Operation operations where operations.orgRepresentative.id = :id order by operations.dateTime desc")
    Page<Operation> findAllByOrgRepresentativeId(Long id, Pageable pageable);

    boolean existsByOrgRepresentativeId(Long id);

    Long countAllByUserId(Long id);

    Long countAllByOrgRepresentativeId(Long id);

    @Query("select count(operations) from Operation operations where operations.dateTime >= :dateTime and operations.user.id = :id")
    Long countAllByUserIdAndDateTimeAfter(Long id, LocalDateTime dateTime);

    @Query("select count(operations) from Operation operations where operations.dateTime >= :dateTime and operations.orgRepresentative.id = :id")
    Long countAllByOrgRepresentativeIdAndDateTimeAfter(Long id, LocalDateTime dateTime);

    @Query("select operations from Operation operations order by operations.dateTime desc")
    Page<Operation> findAll(Pageable pageable);
}

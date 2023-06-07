package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.OperationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationService {
    OperationDto saveOperation(OperationDto operationDto);

    OperationDto findOperationById(Long id);

    Page<OperationDto> findAllOperationsByUserEmail(Pageable pageable, String email);

    Page<OperationDto> findAllOperationsByOrgRepresentativeId(Pageable pageable, Long id);

    Page<OperationDto> findAllOperationsByUserEmailAndDate(Pageable pageable, String email, LocalDateTime dateTime);

    Page<OperationDto> findAllOperationsByOrgRepresentativeIdAndDate(Pageable pageable, Long id, LocalDateTime dateTime);

    Page<OperationDto> findAllOperations(Pageable pageable);

    List<OperationDto> findAllOperationList();

    Long getOperationsCount();

    OperationDto updateOperation(OperationDto operationDto);

    void deleteOperationById(Long id);

    boolean existsByUserId(Long id);

    boolean existsByOrgRepresentativeId(Long id);

    Long getOperationsCountByUserId(Long id);

    Long getOperationsCountByOrgRepresentativeId(Long id);

    Long getOperationsCountByUserIdAndDate(Long id, LocalDateTime dateTime);

    Long getOperationsCountByOrgRepresentativeIdAndDate(Long id, LocalDateTime dateTime);
}

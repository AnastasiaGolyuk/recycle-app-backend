package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.OperationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationService {
    OperationDto saveOperation(OperationDto operationDto);

    OperationDto findOperationById(Long id);

//    OperationDto findOperationByUserId(Long id);
//
//    OperationDto findOperationByBusinessOwnerId(Long id);

    Page<OperationDto> findAllOperationsByUserEmail(Pageable pageable, String email);

    Page<OperationDto> findAllOperationsByOrgRepresentativeId(Pageable pageable, Long id);

    Page<OperationDto> findAllOperations(Pageable pageable);

    Long getOperationsCount();

    OperationDto updateOperation(OperationDto operationDto);

    void deleteOperationById(Long id);

    boolean existsByUserId(Long id);

    boolean existsByOrgRepresentativeId(Long id);
}

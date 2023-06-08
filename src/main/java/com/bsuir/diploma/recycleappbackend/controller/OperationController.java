package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.OperationDto;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativeDto;
import com.bsuir.diploma.recycleappbackend.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/operations")
public class OperationController {

    private final OperationService operationService;

    @PostMapping
    public OperationDto save(@RequestBody OperationDto operationDto) {
        return operationService.saveOperation(operationDto);
    }

    @GetMapping
    public List<OperationDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                      @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<OperationDto> operationPage = operationService.findAllOperations(PageRequest.of(page, size));
        return new ArrayList<>(operationPage.getContent());
    }

    @GetMapping("/all")
    public List<OperationDto> findAllList() {
        List<OperationDto> operationPage = operationService.findAllOperationList();
        return new ArrayList<>(operationPage);
    }

    @GetMapping("/{id}")
    public OperationDto findById(@PathVariable Long id) {
        return operationService.findOperationById(id);
    }

    @GetMapping("/org-representative/{id}")
    public List<OperationDto> findAllByOrgRepresentativeId(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                           @PathVariable Long id) {
        Page<OperationDto> operationPage = operationService.findAllOperationsByOrgRepresentativeId(PageRequest.of(page, size), id);
        return new ArrayList<>(operationPage.getContent());
    }

    @GetMapping("/users/{email}")
    public List<OperationDto> findAllByUserEmail(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                 @PathVariable String email) {
        Page<OperationDto> operationPage = operationService.findAllOperationsByUserEmail(PageRequest.of(page, size), email);
        return new ArrayList<>(operationPage.getContent());
    }

    @GetMapping("/org-representative-date/{id}")
    public List<OperationDto> findAllByOrgRepresentativeIdAndDate(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                  @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                                  @PathVariable Long id,
                                                                  @RequestParam(name = "date_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDateTime newDateTime = LocalDateTime.of(date, LocalTime.now());
        Page<OperationDto> operationPage = operationService.findAllOperationsByOrgRepresentativeIdAndDate(PageRequest.of(page, size), id, newDateTime);
        return new ArrayList<>(operationPage.getContent());
    }

    @GetMapping("/users-date/{email}")
    public List<OperationDto> findAllByUserEmailAndDate(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                        @PathVariable String email, @RequestParam(name = "date_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDateTime newDateTime = LocalDateTime.of(date, LocalTime.now());
        Page<OperationDto> operationPage = operationService.findAllOperationsByUserEmailAndDate(PageRequest.of(page, size), email, newDateTime);
        return new ArrayList<>(operationPage.getContent());
    }

    @GetMapping("/exist-by-users/{id}")
    public boolean existByUserId(@PathVariable Long id) {
        return operationService.existsByUserId(id);
    }

    @GetMapping("/exist-by-org-rep-id/{id}")
    public boolean existByOrgRepId(@PathVariable Long id) {
        return operationService.existsByOrgRepresentativeId(id);
    }

    @PatchMapping
    public OperationDto update(@RequestBody OperationDto operationDto) {
        return operationService.updateOperation(operationDto);
    }

    @GetMapping("/count")
    public Long getOperationsCount() {
        return operationService.getOperationsCount();
    }

    @GetMapping("/user/{id}/count")
    public Long getOperationsCountByUserId(@PathVariable Long id) {
        return operationService.getOperationsCountByUserId(id);
    }

    @GetMapping("/org-rep/{id}/count")
    public Long getOperationsCountByOrgRepId(@PathVariable Long id) {
        return operationService.getOperationsCountByOrgRepresentativeId(id);
    }

    @GetMapping("/org-rep-date/{id}/count")
    public Long getOperationsCountByOrgRepIdAndDate(@PathVariable Long id,
                                                    @RequestParam(name = "date_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDateTime newDateTime = LocalDateTime.of(date, LocalTime.now());
        return operationService.getOperationsCountByOrgRepresentativeIdAndDate(id, newDateTime);
    }

    @GetMapping("/user-date/{id}/count")
    public Long getOperationsCountByUserIdAndDate(@PathVariable Long id,
                                                  @RequestParam(name = "date_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        LocalDateTime newDateTime = LocalDateTime.of(date, LocalTime.now());
        return operationService.getOperationsCountByUserIdAndDate(id, newDateTime);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        operationService.deleteOperationById(id);
    }
}

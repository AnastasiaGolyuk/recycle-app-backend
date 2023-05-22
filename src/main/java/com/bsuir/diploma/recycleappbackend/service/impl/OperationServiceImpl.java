package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.OperationDto;
import com.bsuir.diploma.recycleappbackend.model.dto.OperationPrepareDto;
import com.bsuir.diploma.recycleappbackend.model.dto.RecycleSymbolDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import com.bsuir.diploma.recycleappbackend.model.entity.Operation;
import com.bsuir.diploma.recycleappbackend.model.entity.RecycleSymbolType;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import com.bsuir.diploma.recycleappbackend.model.mapper.OperationMapper;
import com.bsuir.diploma.recycleappbackend.repository.OrgRepresentativeRepository;
import com.bsuir.diploma.recycleappbackend.repository.OperationRepository;
import com.bsuir.diploma.recycleappbackend.repository.UserRepository;
import com.bsuir.diploma.recycleappbackend.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final UserRepository userRepository;

    private final OrgRepresentativeRepository orgRepresentativeRepository;
    private final OperationMapper operationMapper;

    @Transactional
    @Override
    public OperationDto saveOperation(OperationDto operationDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        OperationPrepareDto operationPrepareDto = getOperationPrepareDto(operationDto);
        Operation entity = operationMapper.toEntity(operationDto);
        entity.setDateTime(localDateTime);
        entity.setUser(operationPrepareDto.getUser());
        entity.setOrgRepresentative(operationPrepareDto.getOrgRepresentative());
        Operation savedOperation = operationRepository.save(entity);
        return operationMapper.toDto(savedOperation);
    }

    @Override
    public OperationDto findOperationById(Long id) {
        return operationRepository.findById(id)
                .map(operationMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
    }

    @Override
    public Page<OperationDto> findAllOperationsByUserEmail(Pageable pageable, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        List<OperationDto> operationDtoList = operationRepository.findAllByUserId(user.getId(),pageable)
                .stream()
                .map(operationMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(operationDtoList, pageable, operationRepository.count());
    }

    @Override
    public Page<OperationDto> findAllOperationsByOrgRepresentativeId(Pageable pageable, Long id) {

        OrgRepresentative orgRepresentative = orgRepresentativeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        List<OperationDto> operationDtoList = operationRepository.findAllByOrgRepresentativeId(orgRepresentative.getId(),pageable)
                .stream()
                .map(operationMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(operationDtoList, pageable, operationRepository.count());
    }

    @Override
    public Page<OperationDto> findAllOperations(Pageable pageable) {
        List<OperationDto> operationDtoList = operationRepository.findAll(pageable)
                .stream()
                .map(operationMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(operationDtoList, pageable, operationRepository.count());
    }

    @Override
    public Long getOperationsCount() {
        return operationRepository.count();
    }

    @Transactional
    @Override
    public OperationDto updateOperation(OperationDto operationDto) {
        operationRepository.findById(operationDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));

        OperationPrepareDto operationPrepareDto = getOperationPrepareDto(operationDto);
        Operation entity = operationMapper.toEntity(operationDto);
        entity.setUser(operationPrepareDto.getUser());
        entity.setOrgRepresentative(operationPrepareDto.getOrgRepresentative());
        Operation updatedOperation = operationRepository.save(entity);
        return operationMapper.toDto(updatedOperation);
    }

    @Transactional
    @Override
    public void deleteOperationById(Long id) {
        operationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        operationRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserId(Long id) {
        return operationRepository.existsByUserId(id);
    }

    @Override
    public boolean existsByOrgRepresentativeId(Long id) {
        return operationRepository.existsByOrgRepresentativeId(id);
    }


    private OperationPrepareDto getOperationPrepareDto(OperationDto operationDto) {
        User user = userRepository.findById(operationDto.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_ERROR"));
        OrgRepresentative orgRepresentative = orgRepresentativeRepository.findById(operationDto.getOrgRepresentative().getId())
                .orElseThrow(() -> new EntityNotFoundException("BUSINESS_OWNER_NOT_FOUND_ERROR"));

        return new OperationPrepareDto(user, orgRepresentative);
    }
}

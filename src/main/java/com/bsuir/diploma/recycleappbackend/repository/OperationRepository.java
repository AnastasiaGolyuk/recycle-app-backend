package com.bsuir.diploma.recycleappbackend.repository;


import com.bsuir.diploma.recycleappbackend.model.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}

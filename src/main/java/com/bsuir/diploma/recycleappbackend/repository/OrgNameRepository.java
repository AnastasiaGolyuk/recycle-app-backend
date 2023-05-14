package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrgNameRepository extends JpaRepository<OrgName, Long> {

    Optional<OrgName> findOrgByName(String name);

    boolean existsByName(String name);
}

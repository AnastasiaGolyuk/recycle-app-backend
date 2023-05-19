package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OrgNameRepository extends JpaRepository<OrgName, Long> {

    Optional<OrgName> findOrgByName(String name);

    boolean existsByName(String name);
}

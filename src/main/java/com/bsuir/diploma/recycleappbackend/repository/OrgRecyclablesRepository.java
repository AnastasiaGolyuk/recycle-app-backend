package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.OrgRecyclables;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgRecyclables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface OrgRecyclablesRepository extends JpaRepository<OrgRecyclables, Long> {

    Page<OrgRecyclables> findOrgRecyclablesByOrgName_Name(String orgName, Pageable pageable);

    Optional<OrgRecyclables> findOrgRecyclablesByType(String type);

    Long countAllByOrgName_Name(String name);
}

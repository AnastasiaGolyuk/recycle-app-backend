package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepresentativeRepository extends JpaRepository<OrgRepresentative, Long> {
   
}

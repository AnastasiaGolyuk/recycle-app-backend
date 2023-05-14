package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgRepresentativeRepository extends JpaRepository<OrgRepresentative, Long> {

    Optional<OrgRepresentative> findOrgRepresentativeByUserId(Long id);

    Optional<OrgRepresentative> findOrgRepresentativeByOrgNameId(Long id);

}

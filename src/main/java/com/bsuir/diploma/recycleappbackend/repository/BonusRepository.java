package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.Bonus;
import com.bsuir.diploma.recycleappbackend.model.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {

    Optional<Bonus> findBonusByUserId(Long id);

    boolean existsByUserEmail(String email);
}

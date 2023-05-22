package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.BonusDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BonusService {

    BonusDto saveBonus(BonusDto bonusDto);

    boolean existsByEmail(String email);

    BonusDto findBonusById(Long id);

    BonusDto findBonusByUserEmail(String email);

    BonusDto updateBonus(BonusDto bonusDto);

    void deleteBonusById(Long id);

    Long getBonusesCount();
}

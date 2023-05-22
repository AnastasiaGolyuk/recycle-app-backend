package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.BonusDto;
import com.bsuir.diploma.recycleappbackend.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bonuses")
public class BonusController {

    private final BonusService bonusService;

    @PostMapping
    public BonusDto save(@RequestBody BonusDto bonusDto) {
        return bonusService.saveBonus(bonusDto);
    }

    @GetMapping("/{id}")
    public BonusDto findById(@PathVariable Long id) {
        return bonusService.findBonusById(id);
    }

    @GetMapping("/email/{email}")
    public BonusDto findBonusByEmail(@PathVariable String email) {
        return bonusService.findBonusByUserEmail(email);
    }

    @GetMapping("/exists-by-email/{email}")
    public boolean existsByEmail(@PathVariable String email) {
        return bonusService.existsByEmail(email);
    }

    @PatchMapping
    public BonusDto update(@RequestBody BonusDto bonusDto) {
        return bonusService.updateBonus(bonusDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bonusService.deleteBonusById(id);
    }

    @GetMapping("/count")
    public Long getBonusesCount() {
        return bonusService.getBonusesCount();
    }
}

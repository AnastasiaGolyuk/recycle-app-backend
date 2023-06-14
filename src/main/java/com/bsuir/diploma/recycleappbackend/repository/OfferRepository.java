package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select offers from Offer offers order by offers.date desc")
    Page<Offer> findAll(Pageable pageable);

    @Query("select offers from Offer offers where offers.valueBonuses<= :value order by offers.date desc")
    Page<Offer> findAllByValueBonusesIsLessThanEqual(Pageable pageable, Double value);
}

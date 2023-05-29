package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.OfferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OfferService {

    OfferDto saveOffer(OfferDto offerDto);

    OfferDto findOfferById(Long id);

    Page<OfferDto> findAllOffers(Pageable pageable);

    Page<OfferDto> findAllOffersByValueAndLess(Pageable pageable, Double value);

    OfferDto updateOffer(OfferDto offerDto);

    void deleteOfferById(Long id);

    Long getOffersCount();
}

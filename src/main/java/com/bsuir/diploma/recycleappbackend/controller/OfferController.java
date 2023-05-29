package com.bsuir.diploma.recycleappbackend.controller;


import com.bsuir.diploma.recycleappbackend.model.dto.OfferDto;
import com.bsuir.diploma.recycleappbackend.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public OfferDto save(@RequestBody OfferDto offerDto) {
        return offerService.saveOffer(offerDto);
    }

    @GetMapping
    public List<OfferDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                 @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<OfferDto> offerPage = offerService.findAllOffers(PageRequest.of(page, size));
        System.out.println(page);
        return new ArrayList<>(offerPage.getContent());
    }

    @GetMapping("/{id}")
    public OfferDto findById(@PathVariable Long id) {
        return offerService.findOfferById(id);
    }

    @GetMapping("/value/{value}")
    public List<OfferDto> findOfferByValueAndLess(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                             @RequestParam(name = "size", defaultValue = "10") Integer size,
                                             @PathVariable Double value) {
        Page<OfferDto> offerPage = offerService.findAllOffersByValueAndLess(PageRequest.of(page, size),value);
        return new ArrayList<>(offerPage.getContent());
    }


    @PatchMapping
    public OfferDto update(@RequestBody OfferDto offerDto) {
        return offerService.updateOffer(offerDto);
    }

    @GetMapping("/count")
    public Long getOffersCount() {
        return offerService.getOffersCount();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        offerService.deleteOfferById(id);
    }
}
package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.model.dto.OfferDto;
import com.bsuir.diploma.recycleappbackend.model.entity.Offer;
import com.bsuir.diploma.recycleappbackend.model.mapper.OfferMapper;
import com.bsuir.diploma.recycleappbackend.repository.OfferRepository;
import com.bsuir.diploma.recycleappbackend.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    @Transactional
    @Override
    public OfferDto saveOffer(OfferDto offerDto) {
        Offer entity = offerMapper.toEntity(offerDto);
        Offer savedOffer = offerRepository.save(entity);
        return offerMapper.toDto(savedOffer);
    }


    @Override
    public OfferDto findOfferById(Long id) {
        return offerRepository.findById(id)
                .map(offerMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("offer_NOT_FOUND_ERROR"));
    }

    @Override
    public Page<OfferDto> findAllOffers(Pageable pageable) {
        List<OfferDto> offerDtoList = offerRepository.findAll(pageable)
                .stream()
                .map(offerMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(offerDtoList, pageable, offerRepository.count());
    }

    @Override
    public Page<OfferDto> findAllOffersByValueAndLess(Pageable pageable, Double value) {
        List<OfferDto> offerDtoList = offerRepository.findAllByValueBonusesIsLessThanEqual(pageable, value)
                .stream()
                .map(offerMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(offerDtoList, pageable, offerRepository.count());

    }


    @Transactional
    @Override
    public OfferDto updateOffer(OfferDto offerDto) {
        offerRepository.findById(offerDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        Offer entity = offerMapper.toEntity(offerDto);
        Offer updatedOffer = offerRepository.save(entity);
        return offerMapper.toDto(updatedOffer);
    }

    @Transactional
    @Override
    public void deleteOfferById(Long id) {
        offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CLIENT_NOT_FOUND_ERROR"));
        offerRepository.deleteById(id);
    }

    @Override
    public Long getOffersCount() {
        return offerRepository.count();
    }

}

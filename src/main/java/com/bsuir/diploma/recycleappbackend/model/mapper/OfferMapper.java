package com.bsuir.diploma.recycleappbackend.model.mapper;


import com.bsuir.diploma.recycleappbackend.model.dto.OfferDto;
import com.bsuir.diploma.recycleappbackend.model.entity.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferDto toDto (Offer offer);

    Offer toEntity(OfferDto offerDto);
}

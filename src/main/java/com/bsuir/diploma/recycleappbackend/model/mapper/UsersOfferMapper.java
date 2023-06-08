package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.UsersOfferDto;
import com.bsuir.diploma.recycleappbackend.model.entity.UsersOffer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, OfferMapper.class})

public interface UsersOfferMapper {

    UsersOfferDto toDto(UsersOffer usersOffer);

    UsersOffer toEntity(UsersOfferDto usersOfferDto);
}

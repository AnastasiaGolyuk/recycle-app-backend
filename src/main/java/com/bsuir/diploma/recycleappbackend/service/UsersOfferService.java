package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.UsersOfferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersOfferService {

    UsersOfferDto saveUsersOffer(UsersOfferDto usersOfferDto);

    Page<UsersOfferDto> findUsersOfferByUserId(Pageable pageable, Long userId);

    UsersOfferDto findUsersOfferById(Long id);

    Page<UsersOfferDto> findAllUsersOffers(Pageable pageable);

    Long getUsersOffersCount();

    Long getUsersOffersCountByUserId(Long id);

    UsersOfferDto updateUsersOffer(UsersOfferDto clientDto);

    void deleteUsersOfferById(Long id);
}

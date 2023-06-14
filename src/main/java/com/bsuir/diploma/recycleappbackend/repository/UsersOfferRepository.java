package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.Offer;
import com.bsuir.diploma.recycleappbackend.model.entity.Operation;
import com.bsuir.diploma.recycleappbackend.model.entity.UsersOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersOfferRepository  extends JpaRepository<UsersOffer, Long> {

    @Query("select users_offer from UsersOffer users_offer where users_offer.user.id = :userId order by users_offer.offer.date desc")
    Page<UsersOffer> findAllByUserId(Pageable pageable, Long userId);

    Long countAllByUserId(Long userId);
}

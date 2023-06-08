package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.Operation;
import com.bsuir.diploma.recycleappbackend.model.entity.UsersOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface UsersOfferRepository  extends JpaRepository<UsersOffer, Long> {

    Page<UsersOffer> findAllByUserId(Pageable pageable, Long userId);

    Long countAllByUserId(Long userId);
}

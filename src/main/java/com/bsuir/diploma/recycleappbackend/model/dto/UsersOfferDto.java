package com.bsuir.diploma.recycleappbackend.model.dto;


import com.bsuir.diploma.recycleappbackend.model.entity.Offer;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersOfferDto {
    Long id;
    User user;
    Offer offer;
    boolean isActive;
}

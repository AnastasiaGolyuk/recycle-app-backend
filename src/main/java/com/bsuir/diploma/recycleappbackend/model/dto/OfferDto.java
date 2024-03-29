package com.bsuir.diploma.recycleappbackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {

    private Long id;

    private String title;

    private String description;

    private Double valueBonuses;

    private LocalDate date;
}

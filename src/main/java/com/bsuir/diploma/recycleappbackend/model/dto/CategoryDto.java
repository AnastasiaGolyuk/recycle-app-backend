package com.bsuir.diploma.recycleappbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    private String description;

    private String recycleInfo;

    private String recyclable;

    private String notRecyclable;

    private String imgLink;
}

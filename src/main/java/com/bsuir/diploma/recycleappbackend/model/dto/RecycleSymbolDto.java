package com.bsuir.diploma.recycleappbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecycleSymbolDto {

    private Long id;


    private String name;


    private String description;


    private String imgLink;
}


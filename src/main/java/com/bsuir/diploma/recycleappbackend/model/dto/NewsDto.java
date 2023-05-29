package com.bsuir.diploma.recycleappbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {

        private Long id;

        private String title;

        private String subject;

        private String source;

        private String imgLink;

        private LocalDate date;
}

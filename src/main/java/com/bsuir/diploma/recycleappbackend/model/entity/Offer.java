package com.bsuir.diploma.recycleappbackend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "offer")
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "title")
    private String title;

    @Column(name = "value_bonuses")
    private Double valueBonuses;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;
}

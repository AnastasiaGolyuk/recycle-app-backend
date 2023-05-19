package com.bsuir.diploma.recycleappbackend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "recycle_info")
    private String recycleInfo;

    @Column(name = "recyclable")
    private String recyclable;

    @Column(name = "not_recyclable")
    private String notRecyclable;

    @Column(name = "img_link")
    private String imgLink;
}

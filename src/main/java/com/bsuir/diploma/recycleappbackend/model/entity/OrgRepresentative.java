package com.bsuir.diploma.recycleappbackend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "org_representative")
@NoArgsConstructor
public class OrgRepresentative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "org_name")
    private String org_name;

    @Column(name = "position")
    private String position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}

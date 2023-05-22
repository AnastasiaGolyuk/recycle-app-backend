package com.bsuir.diploma.recycleappbackend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "operation")
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "org_representative_id", referencedColumnName = "id")
    private OrgRepresentative orgRepresentative;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "amount_bonuses")
    private String amountBonuses;

    @Column(name = "type_recyclables")
    private String typeRecyclables;

    @Column(name = "amount_recyclables")
    private String amountRecyclables;
}

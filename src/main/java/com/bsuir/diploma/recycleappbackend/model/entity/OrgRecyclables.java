package com.bsuir.diploma.recycleappbackend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "org_recyclables")
@NoArgsConstructor
public class OrgRecyclables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "org_name_id", referencedColumnName = "id")
    private OrgName orgName;

    @Column(name = "value_bonuses")
    private String valueBonuses;

    @Column(name = "type")
    private String type;
}

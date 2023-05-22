package com.bsuir.diploma.recycleappbackend.model.dto;

import com.bsuir.diploma.recycleappbackend.model.entity.OrgName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgRecyclablesDto {

    private Long id;

    private OrgName orgName;

    private String valueBonuses;

    private String type;
}

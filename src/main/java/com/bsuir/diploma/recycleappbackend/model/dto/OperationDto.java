package com.bsuir.diploma.recycleappbackend.model.dto;

import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {
    private Long id;

    private User user;

    private OrgRepresentative orgRepresentative;

    private LocalDateTime dateTime;

    private String amountBonuses;

    private String typeRecyclables;

    private String amountRecyclables;
}

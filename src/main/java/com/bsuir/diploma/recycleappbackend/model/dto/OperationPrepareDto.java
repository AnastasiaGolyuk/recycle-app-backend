package com.bsuir.diploma.recycleappbackend.model.dto;

import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationPrepareDto {

    User user;
    OrgRepresentative orgRepresentative;
}

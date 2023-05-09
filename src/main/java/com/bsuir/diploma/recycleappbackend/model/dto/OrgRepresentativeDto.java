package com.bsuir.diploma.recycleappbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgRepresentativeDto {
    private Long id;
    private UserDto userDto;
    private String org_name;
    private String position;
}

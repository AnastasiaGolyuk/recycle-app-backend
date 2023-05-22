package com.bsuir.diploma.recycleappbackend.model.dto;

import com.bsuir.diploma.recycleappbackend.model.entity.Role;
import com.bsuir.diploma.recycleappbackend.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String password;

    private String phoneNumber;

    private String email;

    private Role role;

    private Status status;
}

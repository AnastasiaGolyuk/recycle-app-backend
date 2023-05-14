package com.bsuir.diploma.recycleappbackend.service;

import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto findUserByEmail(String email);

    boolean existsByEmail(String email);

    UserDto findUserById(Long id);

    Page<UserDto> findAllUsers(Pageable pageable);

    Long getUsersCount();

    UserDto updateUser(UserDto clientDto);

    void deleteUserById(Long id);

}

package com.bsuir.diploma.recycleappbackend.service;


import com.bsuir.diploma.recycleappbackend.model.dto.AuthenticationRequest;
import com.bsuir.diploma.recycleappbackend.model.dto.AuthenticationResponse;
import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import org.springframework.security.core.AuthenticationException;


public interface AuthenticationService {

    UserDto signup(UserDto userDto);


    AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws AuthenticationException;
}
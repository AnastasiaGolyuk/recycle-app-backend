package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.AuthenticationRequest;
import com.bsuir.diploma.recycleappbackend.model.dto.AuthenticationResponse;
import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import com.bsuir.diploma.recycleappbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public UserDto signup(@Valid @RequestBody UserDto userDto) {
        return authenticationService.signup(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }
}

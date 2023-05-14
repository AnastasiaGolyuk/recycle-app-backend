package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.UnauthorizedException;
import com.bsuir.diploma.recycleappbackend.exception.ValidationException;
import com.bsuir.diploma.recycleappbackend.model.dto.AuthenticationRequest;
import com.bsuir.diploma.recycleappbackend.model.dto.AuthenticationResponse;
import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import com.bsuir.diploma.recycleappbackend.model.entity.Role;
import com.bsuir.diploma.recycleappbackend.model.entity.Status;
import com.bsuir.diploma.recycleappbackend.service.AuthenticationService;
import com.bsuir.diploma.recycleappbackend.service.UserService;
import com.bsuir.diploma.recycleappbackend.service.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto signup(UserDto userDto) {
        if (userService.existsByEmail(userDto.getEmail())) {
            throw new ValidationException("User with same email already exist");
        }

        userDto.setRole(Role.USER);
        userDto.setStatus(Status.ACTIVE);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userService.saveUser(userDto);
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        String email = authenticationRequest.getEmail();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email, authenticationRequest.getPassword());

        authenticateToken(authenticationToken);

        UserDto userDto = userService.findUserByEmail(email);

        String token = jwtTokenProvider.createToken(email, userDto.getRole());
        return new AuthenticationResponse(email, token);
    }

    private void authenticateToken(UsernamePasswordAuthenticationToken authenticationToken) {
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException ex) {
            System.out.println(ex);
            throw new UnauthorizedException("Invalid email/password combination");
        }
    }

}

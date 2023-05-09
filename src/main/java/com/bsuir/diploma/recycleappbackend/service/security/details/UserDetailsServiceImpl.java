package com.bsuir.diploma.recycleappbackend.service.security.details;

import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import com.bsuir.diploma.recycleappbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDto userDto = userService.findUserByEmail(email);
        return SecurityUser.fromUserDto(userDto);
    }
}
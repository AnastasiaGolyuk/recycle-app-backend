package com.bsuir.diploma.recycleappbackend.service.security.details;

import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import com.bsuir.diploma.recycleappbackend.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {
    private final String userName;
    private final String password;
    private final boolean isActive;
    private final List<SimpleGrantedAuthority> authorities;

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUserDto(UserDto userDto) {
        return new org.springframework.security.core.userdetails.User(
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getStatus().equals(Status.ACTIVE),
                userDto.getStatus().equals(Status.ACTIVE),
                userDto.getStatus().equals(Status.ACTIVE),
                userDto.getStatus().equals(Status.ACTIVE),
                userDto.getRole().getAuthorities()
        );
    }
}

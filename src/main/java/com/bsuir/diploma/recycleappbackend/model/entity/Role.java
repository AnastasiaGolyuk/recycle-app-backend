package com.bsuir.diploma.recycleappbackend.model.entity;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Role {

    USER(Set.of(Permission.USER_PERMISSION)),
    ORG_REPRESENTATIVE(Set.of(Permission.USER_PERMISSION ,Permission.ORG_REPRESENTATIVE_PERMISSION)),
    ADMIN(Set.of(Permission.USER_PERMISSION, Permission.ADMIN_PERMISSION));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}


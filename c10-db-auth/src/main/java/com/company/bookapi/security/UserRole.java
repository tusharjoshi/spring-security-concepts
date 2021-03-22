package com.company.bookapi.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.company.bookapi.security.UserPermission.*;

public enum UserRole {
    MEMBER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(BOOK_READ, BOOK_WRITE, BUNDLE_READ, BUNDLE_WRITE)),
    ADMIN_SUB_CON(Sets.newHashSet(BOOK_READ, BUNDLE_READ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}

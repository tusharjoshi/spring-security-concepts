package com.company.bookapi.security;

public enum UserPermission {
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write"),
    BUNDLE_READ("bundle:read"),
    BUNDLE_WRITE("bundle:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

package com.sashel.user_profile_service.user.enums;

public enum UserRole {
    SELLER("seller"),
    BUYER("buyer"),
    ADMIN("admin");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

package com.giatrong.learning.learnspringapi.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }
}

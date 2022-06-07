package com.spring.common.enums;

import org.springframework.stereotype.Component;

@Component

public enum UserStatus {
    ACTIVE("active"), INACTIVE("inactive");

    String status;
    UserStatus(String status) {
        this.status = status;
    }

}

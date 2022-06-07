package com.spring.common.enums;

import org.springframework.stereotype.Component;

@Component
public class Common {

    public enum UserStatus {
        active("active"), inactive("inactive");

        String status;
        UserStatus(String status) {
            this.status = status;
        }

    }
}

package com.spring.oauth2.config.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.datasource.slave")
public class SlaveDetails {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

}

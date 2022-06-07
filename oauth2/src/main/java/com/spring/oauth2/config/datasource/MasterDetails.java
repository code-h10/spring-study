package com.spring.oauth2.config.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.datasource.master")
public class MasterDetails {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}

package com.spring.social.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.apple")
public class AppleProperties {
    private String oauthHost;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;
    private String status;
}

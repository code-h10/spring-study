package com.spring.social.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.kakao")
public class KaKaoProperties {

    private String oauthHost;
    private String kApiHost;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String state;

}



package com.slt.documentmanagment.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:auth.properties")
@ConfigurationProperties
@Data
public class OauthConfig {
    private String oauthServer;
    private String clientSecret;
    private String clientId;
}

package com.slt.documentmanagment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:resourceserver.properties")
@ConfigurationProperties
@Data
public class ResourceServerPropConfig {
    private String resourceServerUrl;
}
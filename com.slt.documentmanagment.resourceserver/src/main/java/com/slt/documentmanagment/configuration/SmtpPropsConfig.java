package com.slt.documentmanagment.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:smtp.properties")
@ConfigurationProperties
@Data
public class SmtpPropsConfig {

    private String emailHost;
    private int smtpPort;
    private String debug;
    private String user;
    private String password;
    private String from;
    private int emailWorkers;

}
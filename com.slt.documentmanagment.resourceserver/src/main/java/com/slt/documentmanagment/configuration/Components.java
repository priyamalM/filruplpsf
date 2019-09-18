package com.slt.documentmanagment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Components {


    //192.168.39.79
    @Bean
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("192.168.39.79");
        mailSender.setPort(587);

        mailSender.setUsername("sltdms");
        mailSender.setPassword("cosmos@#321");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.starttls.required","false");
        props.put("mail.debug", "true");

        return mailSender;
    }

}

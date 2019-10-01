package com.slt.documentmanagment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Components {



    final String USERNAME;
    final String PASS;
    final String HOST;
    final int PORT;

    @Autowired
    public Components(SmtpPropsConfig smtpPropsConfig){
        USERNAME = smtpPropsConfig.getUser();
        PASS = smtpPropsConfig.getPassword();
        PORT = smtpPropsConfig.getSmtpPort();
        HOST = smtpPropsConfig.getEmailHost();
    }

    //192.168.39.79
    @Bean
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(HOST);
        mailSender.setPort(PORT);
        mailSender.setUsername(USERNAME);
        mailSender.setPassword(PASS);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.starttls.required","false");
        props.put("mail.debug","true");

        return mailSender;
    }

}

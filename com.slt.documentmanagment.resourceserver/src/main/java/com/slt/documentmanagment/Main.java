package com.slt.documentmanagment;

import com.slt.documentmanagment.configuration.Components;
import com.slt.documentmanagment.configuration.OauthConfig;
import com.slt.documentmanagment.configuration.SmtpPropsConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({Components.class})
@EnableConfigurationProperties({SmtpPropsConfig.class, OauthConfig.class})
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
    }


}
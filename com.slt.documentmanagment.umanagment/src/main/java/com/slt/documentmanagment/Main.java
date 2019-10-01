package com.slt.documentmanagment;

import com.slt.documentmanagment.config.ResourceServerPropConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ResourceServerPropConfig.class})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

}

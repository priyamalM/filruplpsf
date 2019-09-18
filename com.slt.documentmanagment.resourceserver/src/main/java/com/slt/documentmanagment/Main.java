package com.slt.documentmanagment;

import com.slt.documentmanagment.configuration.Components;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Import({Components.class})
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }


}
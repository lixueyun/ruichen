package com.ruichen.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulApplication.class, args);
    }

}

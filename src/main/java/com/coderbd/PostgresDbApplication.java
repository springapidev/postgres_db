package com.coderbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PostgresDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresDbApplication.class, args);
    }

}

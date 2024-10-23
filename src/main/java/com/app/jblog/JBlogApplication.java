package com.app.jblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(JBlogApplication.class, args);
    }

}

package com.lhz.hongbaoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by lhz on 16-8-29.
 */

@SpringBootApplication
//@EnableOAuth2Sso
public class Application{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
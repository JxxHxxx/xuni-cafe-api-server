package com.xuni.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class XuniCafeApiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuniCafeApiServerApplication.class, args);
        Hooks.onOperatorDebug();
    }

}

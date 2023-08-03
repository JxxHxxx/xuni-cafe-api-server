package com.xuni.cafe;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class XuniCafeApiServerApplication {


    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(XuniCafeApiServerApplication.class, args);
        Hooks.onOperatorDebug();
    }

}

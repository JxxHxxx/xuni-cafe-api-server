package com.xuni.cafe.api.place.presentation;

import com.xuni.cafe.api.common.response.BasicResponseBody;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.DateTimeException;

import static com.xuni.cafe.api.place.dto.response.PlaceAPiMessage.*;

@Order(1)
@ControllerAdvice(basePackages = "com.xuni.cafe.api.place")
public class PlaceExceptionHandler {

    @ExceptionHandler(DateTimeException.class)
    public Mono<ResponseEntity<BasicResponseBody>> handleDateTimeException() {

        return Mono.just(ResponseEntity.badRequest().body(new BasicResponseBody(400, INVALID_WEEKEND_VALUES)));
    }
}

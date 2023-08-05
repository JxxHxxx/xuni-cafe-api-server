package com.xuni.cafe.common.response;

import lombok.Getter;

@Getter
public class SimpleResponseBody<T> {
    private final Integer status;
    private final String message;
    private T response;

    public SimpleResponseBody(Integer status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public SimpleResponseBody(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}

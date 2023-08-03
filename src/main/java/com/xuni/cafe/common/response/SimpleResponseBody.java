package com.xuni.cafe.common.response;

import lombok.Getter;

@Getter
public class SimpleResponseBody<T> {
    private final Integer code;
    private final String message;
    private T response;

    public SimpleResponseBody(Integer code, String message, T response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public SimpleResponseBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

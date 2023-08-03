package com.xuni.cafe.common.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ListResponseBody<T> {
    private final Integer code;
    private final List<String> message;
    private T response;

    public ListResponseBody(Integer code, List<String> message, T response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public ListResponseBody(Integer code, List<String> message) {
        this.code = code;
        this.message = message;
    }
}

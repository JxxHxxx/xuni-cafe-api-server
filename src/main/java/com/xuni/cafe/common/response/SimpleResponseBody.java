package com.xuni.cafe.common.response;

public record SimpleResponseBody<T>(
        Integer code,
        String message,
        T response
) {
}

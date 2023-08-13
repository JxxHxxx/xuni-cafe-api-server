package com.xuni.cafe.api.common.response;

import lombok.Getter;

@Getter
public class BasicResponseBody {
    private final Integer status;
    private final String message;

    public BasicResponseBody(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}

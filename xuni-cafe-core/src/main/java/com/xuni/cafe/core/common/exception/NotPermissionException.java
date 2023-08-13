package com.xuni.cafe.core.common.exception;

public class NotPermissionException extends RuntimeException {
    public NotPermissionException() {
        super(CommonExceptionMessage.NOT_PERMISSION);
    }

    public NotPermissionException(String message) {
        super(message);
    }
}

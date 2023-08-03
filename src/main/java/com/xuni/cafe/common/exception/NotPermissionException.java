package com.xuni.cafe.common.exception;

import static com.xuni.cafe.common.exception.CommonExceptionMessage.*;

public class NotPermissionException extends RuntimeException {
    public NotPermissionException() {
        super(NOT_PERMISSION);
    }

    public NotPermissionException(String message) {
        super(message);
    }
}

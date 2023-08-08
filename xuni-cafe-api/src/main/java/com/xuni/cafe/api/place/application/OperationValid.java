package com.xuni.cafe.api.place.application;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.xuni.cafe.api.place.dto.response.PlaceAPiMessage.INVALID_OPERATION;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = OperationTimeValidator.class)
public @interface OperationValid {
    String message() default INVALID_OPERATION;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

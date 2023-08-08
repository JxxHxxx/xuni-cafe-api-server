package com.xuni.cafe.api.place.application;

import com.xuni.cafe.api.place.dto.request.PlaceForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperationTimeValidator implements ConstraintValidator<OperationValid, PlaceForm> {

    @Override
    public boolean isValid(PlaceForm placeForm, ConstraintValidatorContext context) {
        return placeForm.opening().isBefore(placeForm.closing());

    }
}


package com.xuni.cafe.api.place.application;

import com.xuni.cafe.api.place.dto.request.PlaceForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class TimeSeriesValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PlaceForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PlaceForm placeForm = (PlaceForm) target;
        if (placeForm.opening().isAfter(placeForm.closing())) {
            errors.reject("invalid.time", "오픈/종료 시간 설정이 올바르지 않습니다.");
        };
    }
}

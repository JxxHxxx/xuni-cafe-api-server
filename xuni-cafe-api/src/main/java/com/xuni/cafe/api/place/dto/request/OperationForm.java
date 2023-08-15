package com.xuni.cafe.api.place.dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public record OperationForm(
        Long ownerId,
        LocalTime opening,
        LocalTime closing,
        List<DayOfWeek> holidays
) {
}

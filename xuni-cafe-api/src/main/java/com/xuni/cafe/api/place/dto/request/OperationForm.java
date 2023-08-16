package com.xuni.cafe.api.place.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public record OperationForm(
        @NotNull
        Long ownerId,
        @NotNull
        LocalTime opening,
        @NotNull
        LocalTime closing,
        List<DayOfWeek> holidays
) {
}

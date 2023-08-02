package com.xuni.cafe.place.domain;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static com.xuni.cafe.place.domain.exception.PlaceExceptionMessage.INCORRECT_OPERATION_TIME;

@Getter
public class Operation {

    private LocalTime opening;
    private LocalTime closing;
    private List<DayOfWeek> holidays;

    public Operation(LocalTime opening, LocalTime closing, List<DayOfWeek> holidays) {
        this.opening = opening;
        this.closing = closing;
        this.holidays = holidays;
    }

    public static Operation of(LocalTime opening, LocalTime closing, List<DayOfWeek> closedDays) {
        return new Operation(opening, closing, closedDays);
    }

    protected void verifyOpeningHours() {
        if (opening.isAfter(closing)) {
            throw new IllegalArgumentException(INCORRECT_OPERATION_TIME);
        }
    }

    protected void setOpening(LocalTime opening){
        this.opening = opening;
    }

    protected void setClosing(LocalTime closing){
        this.closing = closing;
    }

    public void setHolidays(List<DayOfWeek> holidays){
        this.holidays = holidays;
    }
}

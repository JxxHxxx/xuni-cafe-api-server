package com.xuni.cafe.place.domain;

import lombok.Getter;
import org.springframework.web.server.ServerWebInputException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

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

    protected void verifyOperationHours() {
        if (opening.isAfter(closing)) {
            throw new ServerWebInputException(INCORRECT_OPERATION_TIME);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(opening, operation.opening) && Objects.equals(closing, operation.closing) && Objects.equals(holidays, operation.holidays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opening, closing, holidays);
    }
}

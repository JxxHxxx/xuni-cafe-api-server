package com.xuni.cafe.api.schedule.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Weekend {

    private final LocalDate beginDate;
    private final LocalDate endDate;

    private Weekend(LocalDate beginDate, LocalDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public static Weekend of(LocalDate beginDate, LocalDate endDate) {
        return new Weekend(beginDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weekend weekend = (Weekend) o;
        return Objects.equals(beginDate, weekend.beginDate) && Objects.equals(endDate, weekend.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginDate, endDate);
    }
}

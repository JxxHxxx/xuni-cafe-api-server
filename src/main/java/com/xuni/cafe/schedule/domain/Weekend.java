package com.xuni.cafe.schedule.domain;

import java.time.LocalDate;

public class Weekend {

    private final LocalDate beginDate;
    private final LocalDate endDate;

    public Weekend(LocalDate beginDate, LocalDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
}

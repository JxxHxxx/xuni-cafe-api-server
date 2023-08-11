package com.xuni.cafe.api.util;

import com.xuni.cafe.api.schedule.domain.Information;
import com.xuni.cafe.api.schedule.domain.Weekend;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDocumentFactory {

    public static Information information(Long costumerId, LocalDate reserveDate, LocalTime reserveTime) {
        return Information.of(costumerId, reserveDate, reserveTime, 3);
    }

    public static Weekend weekendSample() {
        return Weekend.of(LocalDate.of(2023, 8, 7), LocalDate.of(2023, 8, 13));
    }
}

package com.xuni.cafe.util;

import com.xuni.cafe.place.domain.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class PlaceDocumentFactory {

    public static final LocalTime OPENING_TIME = LocalTime.of(9, 0, 0);
    public static final LocalTime CLOSING_TIME = LocalTime.of(21, 0, 0);
    public static final List<DayOfWeek> CLOSED_DAYS = List.of(DayOfWeek.MONDAY, DayOfWeek.SUNDAY);
    public static final Address ADDRESS = Address.of("서울시", "강남구", "대치동", "220", "202호");

    public static Place place() {
        return Place.builder()
                .name("스타벅스 유니DT점")
                .type(PlaceType.FRANCHISE_CAFE)
                .address(ADDRESS)
                .operation(Operation.of(OPENING_TIME, CLOSING_TIME, CLOSED_DAYS))
                .rooms(List.of(Room.of(1, 4), Room.of(2, 4), Room.of(3, 2)))
                .build();
    }
}
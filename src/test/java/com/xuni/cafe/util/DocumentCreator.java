package com.xuni.cafe.util;

import com.xuni.cafe.place.domain.*;

public class DocumentCreator {

    public static Place place() {
        return Place.builder()
                .name("스타벅스 유니DT점")
                .type(PlaceType.FRANCHISE_CAFE)
                .address(Address.of("서울시", "강남구", "대치동", "220", "202호"))
                .build();
    }
}
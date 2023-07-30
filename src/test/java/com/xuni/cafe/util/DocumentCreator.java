package com.xuni.cafe.util;

import com.xuni.cafe.place.domain.*;

import java.util.List;

public class DocumentCreator {

    public static Place place() {
        return Place.builder()
                .name("스타벅스 유니DT점")
                .recommend(Recommend.of(1l, "유니"))
                .type(PlaceType.FRANCHISE_CAFE)
                .address(Address.of("서울시", "강남구", "대치동", "220", "202호"))
                .tags(List.of(PlaceTag.from(Feature.MANY_OUTLET), PlaceTag.from(Feature.LESS_NOISE)))
                .build();
    }
}
package com.xuni.cafe.place.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class PlaceTest {

    @DisplayName("Place 도큐먼트 초기화 시 기본 필드 체크")
    @Test
    void init() {
        Place place = Place.builder()
                .name("스타벅스 유니DT점")
                .recommend(Recommend.of(1l, "유니"))
                .type(PlaceType.FRANCHISE_CAFE)
                .address(Address.of("서울시", "강남구", "대치동", "220", "202호"))
                .tags(List.of(PlaceTag.from(Feature.MANY_OUTLET), PlaceTag.from(Feature.LESS_NOISE)))
                .build();

        PlaceTag placeTag = place.getTags().get(0);
        Address rowAddress = Address.of("서울시", "강남구", "대치동", "220", "202호");

        assertThat(place).extracting("recommend.flag").isEqualTo(true).as("추천 여부 초기값");
        assertThat(placeTag).extracting("agreement").isEqualTo(0).as("장소 특징에 대한 공감 수 초기값");
        assertThat(rowAddress).isEqualTo(place.getAddress()).as("Address 객체 동등성 테스트");

    }
}
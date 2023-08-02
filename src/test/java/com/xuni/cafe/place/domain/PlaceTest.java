package com.xuni.cafe.place.domain;

import com.xuni.cafe.util.DocumentCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class PlaceTest {

    @DisplayName("Place 도큐먼트 초기화 시 기본 필드 체크")
    @Test
    void init() {
        Place place = DocumentCreator.place();

        Address rowAddress = Address.of("서울시", "강남구", "대치동", "220", "202호");

        assertThat(place).extracting("recommend.flag").isEqualTo(true).as("추천 여부 초기값");
        assertThat(rowAddress).isEqualTo(place.getAddress()).as("Address 객체 동등성 테스트");

    }
}
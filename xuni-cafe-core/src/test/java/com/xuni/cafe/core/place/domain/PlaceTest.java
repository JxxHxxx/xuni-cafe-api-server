package com.xuni.cafe.core.place.domain;

import com.xuni.cafe.core.common.exception.CommonExceptionMessage;
import com.xuni.cafe.core.common.exception.NotPermissionException;
import com.xuni.cafe.core.util.PlaceDocumentFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class PlaceTest {

    @DisplayName("Place 도큐먼트 초기화 시 기본 필드 체크")
    @Test
    void init() {
        Place place = Place.builder()
                .name("스타벅스 유니DT점")
                .type(PlaceType.FRANCHISE_CAFE)
                .ownerId(10l)
                .address(PlaceDocumentFactory.ADDRESS)
                .operation(Operation.of(PlaceDocumentFactory.OPENING_TIME, PlaceDocumentFactory.CLOSING_TIME, PlaceDocumentFactory.CLOSED_DAYS))
                .rooms(List.of(Room.of(1, 4), Room.of(2, 4), Room.of(3, 2)))
                .build();

        Address address =  Address.of("서울시", "강남구", "대치동", "220", "202호");

        List<Room> rooms = place.getRooms();
        String opening = place.getOperation().getOpening().toString();

        assertThat(address).isEqualTo(place.getAddress()).as("Address 객체 동등성 테스트");
        Assertions.assertThat(rooms).extracting("roomNumber").containsExactly(1,2,3);
        Assertions.assertThat(rooms).extracting("capacity").containsExactly(4,4,2);
        assertThat(opening).isEqualTo("09:00");
    }

    @DisplayName("소유자를 검증할 수 있다. 검증에 통과하지 못하는 경우, 예외가 발생한다.")
    @Test
    void verify_owner() {
        Place place = Place.builder()
                .ownerId(1l)
                .build();

        Assertions.assertThatCode(() -> place.verifyOwner(1l))
                        .doesNotThrowAnyException();

        Assertions.assertThatThrownBy(() -> place.verifyOwner(10l))
                .isInstanceOf(NotPermissionException.class)
                .hasMessage(CommonExceptionMessage.NOT_PERMISSION);
    }
}
package com.xuni.cafe.core.place.domain;

import com.xuni.cafe.core.common.exception.NotPermissionException;
import com.xuni.cafe.core.util.PlaceDocumentFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static com.xuni.cafe.core.common.exception.CommonExceptionMessage.NOT_PERMISSION;
import static org.assertj.core.api.Assertions.*;
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
        assertThat(rooms).extracting("roomNumber").containsExactly(1,2,3);
        assertThat(rooms).extracting("capacity").containsExactly(4,4,2);
        assertThat(opening).isEqualTo("09:00");
    }

    @DisplayName("소유자를 검증할 수 있다. 검증에 통과하지 못하는 경우, 예외가 발생한다.")
    @Test
    void verify_owner() {
        Place place = Place.builder()
                .ownerId(1l)
                .build();

        assertThatCode(() -> place.verifyOwner(1l))
                        .doesNotThrowAnyException();

        assertThatThrownBy(() -> place.verifyOwner(10l))
                .isInstanceOf(NotPermissionException.class)
                .hasMessage(NOT_PERMISSION);
    }

    @DisplayName("place 운영 시간을 변경할 수 있다. 메서드 호출 시, 입력된 값으로 변경된다.")
    @Test
    void change_operation() {
        //given
        Operation operation = Operation.of(
                LocalTime.of(12, 0, 0),
                LocalTime.of(20, 0, 0),
                List.of(DayOfWeek.MONDAY));

        Place place = Place.builder()
                .ownerId(1l)
                .operation(operation)
                .build();

        //when
        long operationChangeRequesterId = 1l;

        Operation changedOperation = Operation.of(
                LocalTime.of(14, 0, 0),
                LocalTime.of(22, 0, 0),
                List.of(DayOfWeek.TUESDAY));

        place.changeOperation(operationChangeRequesterId,
                changedOperation.getOpening(),
                changedOperation.getClosing(),
                changedOperation.getHolidays());

        //then
        assertThat(place.getOperation()).extracting("opening").isEqualTo(LocalTime.of(14, 0 ,0));
        assertThat(place.getOperation()).extracting("closing").isEqualTo(LocalTime.of(22, 0 ,0));
        assertThat(place.getOperation()).extracting("holidays").isEqualTo(List.of(DayOfWeek.TUESDAY));
    }

    @DisplayName("place Owner 가 아닌 사람은 변경 권한이 없다. " +
            "즉 메서드 파라미터로 받은 OwnerId 와 Place 도큐먼트의 OwnerId가 일치하지 않을 경우 " +
            "NotPermissionException 이 발생한다.")
    @Test
    void change_operation_fail() {
        //given
        Operation operation = Operation.of(
                LocalTime.of(12, 0, 0),
                LocalTime.of(20, 0, 0),
                List.of(DayOfWeek.MONDAY));

        Place place = Place.builder()
                .ownerId(1l)
                .operation(operation)
                .build();

        //when
        long notOwnerId = 5l;

        Operation changedOperation = Operation.of(
                LocalTime.of(14, 0, 0),
                LocalTime.of(22, 0, 0),
                List.of(DayOfWeek.TUESDAY));

        assertThatThrownBy(() -> place.changeOperation(notOwnerId,
                changedOperation.getOpening(),
                changedOperation.getClosing(),
                changedOperation.getHolidays()))
                .isInstanceOf(NotPermissionException.class)
                .hasMessage(NOT_PERMISSION);

    }
}
package com.xuni.cafe.core.place.domain;

import com.xuni.cafe.core.place.domain.exception.PlaceFieldValidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static com.xuni.cafe.core.place.domain.exception.PlaceExceptionMessage.*;
import static org.assertj.core.api.Assertions.assertThatCode;

class OperationTest {

    @DisplayName("운영 시간이 올바른지 검증한다. 가게 문을 여는 시간은 닫는 시간보다 빨라야 한다. " +
            "해당 규칙을 준수할 경우 어떠한 예외도 발생하지 않는다.")
    @Test
    void verify_opening_hours_success() {
        Operation operation = Operation.of(
                LocalTime.of(9, 0, 0),
                LocalTime.of(21, 0, 0),
                List.of());

        assertThatCode(() -> operation.validateOperationHours())
                .doesNotThrowAnyException();
    }

    @DisplayName("운영 시간이 올바른지 검증한다. 가게 문을 여는 시간은 닫는 시간보다 빨라야 한다. " +
            "해당 규칙을 준수하지 못할 경우 " +
            "IllegalArgumentException 예외" +
            "INCORRECT_OPERATION_TIME 메시지 발생")
    @Test
    void verify_opening_hours_fail() {
        Operation operation = Operation.of(
                LocalTime.of(20, 0, 0),
                LocalTime.of(8, 0, 0),
                List.of());

        assertThatCode(() -> operation.validateOperationHours())
                .isInstanceOf(PlaceFieldValidException.class)
                .hasMessageContaining(INCORRECT_OPERATION_TIME);
    }
}
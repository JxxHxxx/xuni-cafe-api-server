package com.xuni.cafe.api.schedule.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.xuni.cafe.api.util.ScheduleDocumentFactory.*;
import static org.assertj.core.api.Assertions.*;

class ReservationTest {

    @DisplayName("receiveNotCanceledInformation 메서드 실행 시, 취소 되지 않은 예약 정보만 가져온다." +
            "즉, 해당 메소드 호출로 가져온 List<Information> 객체의 모든 canceled 필드는 false 이다.")
    @Test
    void receive_not_canceled_information() {
        LocalDate reserveDate = LocalDate.of(2023, 8, 10);
        LocalTime reserveTime = LocalTime.of(12, 0);
        Reservation reservation = Reservation.init(weekendSample(),
                List.of(information(100l, reserveDate, reserveTime),
                        information(200l, reserveDate, reserveTime)));

        List<Information> information = reservation.getInformations();
        information.get(0).cancel(100l);

        List<Information> notCanceledInformation = reservation.receiveNotCanceledInformation();

        assertThat(notCanceledInformation).extracting("costumerId").containsExactly(200l);
        assertThat(notCanceledInformation).extracting("canceled").containsExactly(false);


    }
}
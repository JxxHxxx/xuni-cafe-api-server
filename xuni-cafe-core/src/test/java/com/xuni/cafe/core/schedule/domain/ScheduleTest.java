package com.xuni.cafe.core.schedule.domain;

import com.xuni.cafe.core.util.ScheduleDocumentFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleTest {

    @DisplayName("스케줄 인스턴스는 아래와 같이 초기화 할 수 있으며 " +
            "예약 취소 여부를 의미하는 canceled 필드는 false로 초기화된다.")
    @Test
    void initialize() {
        //given
        String placeId = UUID.randomUUID().toString();

        // weekly 1 reservations
        Information information1 = ScheduleDocumentFactory.information(100l,
                LocalDate.of(2023, 8, 7),
                LocalTime.of(12, 0));

        Information information2 = ScheduleDocumentFactory.information(101l,
                LocalDate.of(2023, 8, 10),
                LocalTime.of(13, 30));

        Reservation reserveWeekOne = Reservation.init(
                Weekend.of(
                        LocalDate.of(2023, 8, 7),
                        LocalDate.of(2023, 8, 13)),
                List.of(information1, information2));

        // weekly 2 reservations
        Information information3 = ScheduleDocumentFactory.information(200l,
                LocalDate.of(2023, 8, 15),
                LocalTime.of(17, 0));

        Information information4 = ScheduleDocumentFactory.information(201l,
                LocalDate.of(2023, 8, 20),
                LocalTime.of(13, 30));

        Reservation reserveWeekTwo = Reservation.init(
                Weekend.of(
                        LocalDate.of(2023, 8, 14),
                        LocalDate.of(2023, 8, 20)),
                List.of(information3, information4));

        // when - initialize
        Schedule schedule = Schedule.builder()
                .placeId(placeId)
                .reservations(List.of(reserveWeekOne, reserveWeekTwo))
                .build();

        Reservation reservation = schedule.receiveReservationOf(Weekend.of(LocalDate.of(2023, 8, 7), LocalDate.of(2023, 8, 13)));
        List<Information> weeklyOneReservationInfo = reservation.getInformations();

        assertThat(weeklyOneReservationInfo).extracting("canceled").containsOnly(false);
    }
}
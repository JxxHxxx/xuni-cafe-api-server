package com.xuni.cafe.api.schedule.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Document(collection = "schedules")
public class Schedule {

    @Id
    @Field(name = "schedule_id")
    private String id;
    @Field("place_id")
    private String placeId;
    private LocalDateTime createdAt;
    private List<Reservation> reservations;

    @Builder
    public Schedule(String placeId, List<Reservation> reservations) {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.placeId = placeId;
        this.reservations = reservations;
    }

    public Reservation receiveReservationOf(Weekend weekend) {
         return reservations.stream()
                 .filter(reservation -> reservation.checkWeekend(weekend))
                 .findFirst()
                 .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주간 예약"));
    }

}
package com.xuni.cafe.api.place.application;

import com.xuni.cafe.api.place.domain.*;
import com.xuni.cafe.api.place.dto.request.PlaceForm;
import com.xuni.cafe.api.util.ServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@ServiceTest
class PlaceServiceTest {

    @Autowired
    PlaceService placeService;

    @Test
    void enroll_place() {
        //given
        String placeName = "xuni 스터디 카페";
        long ownerId = 1231l;
        LocalTime opening = LocalTime.of(12, 0);
        LocalTime closing = LocalTime.of(18, 0);
        List<Integer> holidays = List.of(1);
        List<Integer> capacities = List.of(4, 4, 2);

        PlaceForm placeForm = new PlaceForm(
                placeName,
                ownerId,
                PlaceType.FRANCHISE_CAFE,
                Address.of("서울시", "엑스구", "유니동", "05353", "202호"),
                opening,
                closing,
                holidays,
                capacities);

        Place place = Place.builder()
                .name(placeName)
                .ownerId(ownerId)
                .type(PlaceType.FRANCHISE_CAFE)
                .address(Address.of("서울시", "엑스구", "유니동", "05353", "202호"))
                .operation(Operation.of(opening, closing, List.of(DayOfWeek.MONDAY)))
                .rooms(List.of(Room.of(1, 4), Room.of(2, 4), Room.of(3, 2)))
                .build();
        //when
        Mono<Place> placeMono = placeService.enrollPlace(placeForm);
        //then
        StepVerifier.create(placeMono)
                .expectNext(place)
                .verifyComplete();
    }

}
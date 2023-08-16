package com.xuni.cafe.api.place.application;

import com.xuni.cafe.api.place.PlaceRepository;
import com.xuni.cafe.api.place.dto.request.OperationForm;
import com.xuni.cafe.api.place.dto.request.PlaceForm;
import com.xuni.cafe.api.util.ServiceTest;
import com.xuni.cafe.core.place.domain.*;
import org.junit.jupiter.api.BeforeEach;
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
    @Autowired
    PlaceRepository placeRepository;

    // BeforeEach 사이클에 만들어지는 Place 의 Id
    String placeId;

    @BeforeEach
    void beforeEach() {
        Place place = Place.builder()
                .name("비포이치")
                .ownerId(50l)
                .type(PlaceType.FRANCHISE_CAFE)
                .address(Address.of("비포시", "엑스구", "유니동", "00010", "204호"))
                .operation(Operation.of(
                        LocalTime.of(9,0,0),
                        LocalTime.of(23, 0, 0),
                        List.of(DayOfWeek.MONDAY)))
                .rooms(List.of(Room.of(1, 4), Room.of(2, 4), Room.of(3, 2)))
                .build();

        placeId = place.getId();

        placeRepository.save(place);
    }

    @Test
    void enroll_place() {
        //given
        String placeName = "xuni 스터디 카페";
        long ownerId = 1231l;
        LocalTime opening = LocalTime.of(12, 0);
        LocalTime closing = LocalTime.of(18, 0);
        List<DayOfWeek> holidays = List.of(DayOfWeek.MONDAY);
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

    // 기존의 운영 시간 시간 opening 09:00:00 closing 23:00:00 holidays Monday
    @Test
    void change_operation() {
        long ownerId =50;

        OperationForm form = new OperationForm(ownerId,
                LocalTime.of(10, 0, 0),
                LocalTime.of(23, 0, 0),
                List.of(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY));

        placeService.changeOperation(placeId, form);
    }

    @Test
    void change_operation_fail() {
        long notOwnerId =100;

        OperationForm form = new OperationForm(notOwnerId,
                LocalTime.of(10, 0, 0),
                LocalTime.of(23, 0, 0),
                List.of(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY));

        placeService.changeOperation(placeId, form);
    }

}
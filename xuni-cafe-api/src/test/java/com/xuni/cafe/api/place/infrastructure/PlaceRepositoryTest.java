package com.xuni.cafe.api.place.infrastructure;

import com.xuni.cafe.api.config.TestMongoDBConfig;
import com.xuni.cafe.api.place.PlaceRepository;
import com.xuni.cafe.core.place.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;


@Import(TestMongoDBConfig.class)
@DataMongoTest
public class PlaceRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Test
//    @Disabled("로컬 환경에서 MongoDB image 가 떴는지 확인하는 용도")
    void init() {
        Mono<Boolean> xuniMono = mongoTemplate.collectionExists("xuni");

        StepVerifier.create(xuniMono)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void save() {
        Address address = Address.of("서울시", "강남구", "대치동", "220", "202호");
        Operation operation = Operation.of(
                LocalTime.of(9, 0, 0),
                LocalTime.of(21, 0, 0),
                List.of(DayOfWeek.MONDAY, DayOfWeek.SUNDAY));

        Place place = Place.builder()
                .name("스타벅스 유니DT점")
                .type(PlaceType.FRANCHISE_CAFE)
                .address(address)
                .operation(operation)
                .rooms(List.of(Room.of(1, 4), Room.of(2, 4), Room.of(3, 2)))
                .build();

        Mono<Place> placeMono = placeRepository.save(place);
        Mono<Place> findPlaceMono = placeRepository.findById(placeMono.map(p -> p.getId()));

        StepVerifier.create(findPlaceMono)
                .expectNext(place)
                .expectComplete()
                .verify();
    }
}

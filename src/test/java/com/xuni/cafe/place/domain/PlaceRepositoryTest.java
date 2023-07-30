package com.xuni.cafe.place.domain;

import com.xuni.cafe.config.MongoDBConfig;
import com.xuni.cafe.util.DocumentCreator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@Import(MongoDBConfig.class)
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
        Place place = DocumentCreator.place();

        Mono<Place> placeMono = placeRepository.save(place);
        Mono<Place> findPlaceMono = placeRepository.findById(placeMono.map(p -> p.getId()));

        StepVerifier.create(findPlaceMono)
                .expectNext(place)
                .expectComplete()
                .verify();
    }
}

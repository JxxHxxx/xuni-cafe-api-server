package com.xuni.cafe.place.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuni.cafe.place.application.PlaceService;
import com.xuni.cafe.place.domain.Address;
import com.xuni.cafe.place.domain.PlaceType;
import com.xuni.cafe.place.dto.request.PlaceForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.List;

import static com.xuni.cafe.place.dto.response.PlaceAPiMessage.*;

@SpringBootTest
@AutoConfigureWebTestClient
class PlaceControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("장소 등록 요청 - 성공")
    @Test
    void save_places_success() throws Exception {
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

        webTestClient.post()
                .uri("/places")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(placeForm), PlaceForm.class)

                .exchange()
                .expectStatus().isCreated()
                .expectBody().jsonPath("$.message", ENROLL);
    }

    @DisplayName("장소 등록 요청 - 실패 : 클라이언트 측의 입력 값에 대한 유효성 미준수")
    @Test
    void save_places_fail_1() throws Exception {
        String placeName = "xuni 스터디 카페";
        long ownerId = 1231l;
        // opening 은 closing 보다 빠를 수 없음
        LocalTime opening = LocalTime.of(21, 0);
        LocalTime closing = LocalTime.of(18, 0);
        // opening 은 closing 보다 빠를 수 없음

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

        webTestClient.post()
                .uri("/places")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(placeForm), PlaceForm.class)

                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().jsonPath("$.message").isEqualTo(INVALID_OPERATION);
    }

    @DisplayName("장소 등록 요청 - 실패 : 클라이언트 측의 입력 값에 대한 유효성 미준수 여러개")
    @Test
    void save_places_fail_2() throws Exception {
        String placeName = "xuni 스터디 카페";
        long ownerId = 1231l;
        // opening 은 closing 보다 빠를 수 없음
        LocalTime opening = LocalTime.of(21, 0);
        LocalTime closing = LocalTime.of(18, 0);
        // opening 은 closing 보다 빠를 수 없음

        // holidays 에는 중복된 값이 들어갈 수 없음
        List<Integer> holidays = List.of(1, 1);
        List<Integer> capacities = List.of(4, 4, 2);

        int expectedErrorMessagesCount = 2;

        PlaceForm placeForm = new PlaceForm(
                placeName,
                ownerId,
                PlaceType.FRANCHISE_CAFE,
                Address.of("서울시", "엑스구", "유니동", "05353", "202호"),
                opening,
                closing,
                holidays,
                capacities);

        webTestClient.post()
                .uri("/places")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(placeForm), PlaceForm.class)

                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().jsonPath("$.message.size()").isEqualTo(expectedErrorMessagesCount);
    }

}
package com.xuni.cafe.place.presentation;

import com.xuni.cafe.common.response.ListResponseBody;
import com.xuni.cafe.common.response.SimpleResponseBody;
import com.xuni.cafe.place.application.PlaceService;
import com.xuni.cafe.place.domain.Place;
import com.xuni.cafe.place.dto.request.PlaceForm;
import com.xuni.cafe.place.dto.response.PlaceResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static com.xuni.cafe.place.dto.response.PlaceAPiMessage.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
public class PlaceController {

    private final PlaceService placeService;
    private final WebClient webClient;

    @Value("${api.server.xuni.uri-test}")
    private String XUNI_REQUEST;

    public PlaceController(PlaceService placeService, @Qualifier("xuniClient") WebClient webClient) {
        this.placeService = placeService;
        this.webClient = webClient;
    }

    @PostMapping("/v2/places")
    public Mono<ResponseEntity<ListResponseBody>> savePlaceV2(@RequestHeader(name = AUTHORIZATION) String authorization,
                                                              @RequestBody @Valid Mono<PlaceForm> formMono) {

        webClient.get().uri(XUNI_REQUEST)
                .header(AUTHORIZATION, authorization)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class,  exception -> Mono.just(exception.getMessage()))
                .subscribe();

        Mono<Place> placeMono = formMono
                .flatMap(form -> placeService.enrollPlace(form));

        return placeMono.map(place -> ResponseEntity
                        .created(URI.create("/places/" + place.getId()))
                        .body(new ListResponseBody(200, List.of(ENROLL), place)))
                .onErrorResume(WebExchangeBindException.class, exception -> Mono.just(ResponseEntity
                        .badRequest()
                        .body(new ListResponseBody(400, getErrorMessages(exception)))));
    }


    @PostMapping("/places")
    public Mono<ResponseEntity<ListResponseBody>> savePlace(@RequestBody @Valid Mono<PlaceForm> formMono) {
        Mono<Place> placeMono = formMono
                .flatMap(form -> placeService.enrollPlace(form));

        return placeMono.map(place -> ResponseEntity
                        .created(URI.create("/places/" + place.getId()))
                        .body(new ListResponseBody(200, List.of(ENROLL), place)))
                        .onErrorResume(WebExchangeBindException.class, exception -> Mono.just(ResponseEntity
                        .badRequest()
                        .body(new ListResponseBody(400, getErrorMessages(exception)))));
    }

    private static List<String> getErrorMessages(WebExchangeBindException exception) {
        return exception.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage()).toList();
    }

    @GetMapping("/places/{place-id}")
    public Mono<ResponseEntity<SimpleResponseBody>> findPlace(@PathVariable("place-id") String placeId) {
        Mono<SimpleResponseBody> bodyMono = placeService.findOne(placeId)
                .map(place -> new SimpleResponseBody<>(200, READ_ONE, PlaceResponse.of(place.getName(), place.getAddress())));

        return bodyMono.map(body -> ResponseEntity.status(OK).body(body));
    }

    @GetMapping("/places")
    public Mono<ResponseEntity<SimpleResponseBody<List<PlaceResponse>>>> findPlaces() {

        Mono<List<PlaceResponse>> placeResponsesMono = placeService.findMany();

        return placeResponsesMono.map(placeResponses -> ResponseEntity.status(OK)
                .body(new SimpleResponseBody<>(200, READ_MANY, placeResponses)));

    }
    @PostMapping("/le/places")
    public Mono<ResponseEntity<ListResponseBody>> savePlacele(@RequestBody @Valid Mono<PlaceForm> formMono) {
        Mono<Place> placeMono = formMono
                .flatMap(form -> placeService.enrollPlace(form));

        return placeMono.map(place -> ResponseEntity.created(URI.create("/places/" + place.getId()))
                        .body(new ListResponseBody(200, List.of("성공"), place)))
                .onErrorResume(WebExchangeBindException.class, exception -> Mono.just(ResponseEntity.badRequest()
                        .body(new ListResponseBody(400, getErrorMessagesle(exception)))))
                .onErrorResume(IllegalArgumentException.class, exception -> Mono.just(ResponseEntity.badRequest()
                        .body(new ListResponseBody(400, List.of(exception.getMessage())))));
    }


    private static List<String> getErrorMessagesle(WebExchangeBindException exception) {
        return exception.getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage()).toList();
    }
}

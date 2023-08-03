package com.xuni.cafe.place.presentation;

import com.xuni.cafe.common.response.ListResponseBody;
import com.xuni.cafe.common.response.SimpleResponseBody;
import com.xuni.cafe.place.application.PlaceService;
import com.xuni.cafe.place.domain.Place;
import com.xuni.cafe.place.dto.request.PlaceForm;
import com.xuni.cafe.place.dto.response.PlaceResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static com.xuni.cafe.place.dto.response.PlaceAPiMessage.*;
import static org.springframework.http.HttpStatus.*;

@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
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

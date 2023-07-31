package com.xuni.cafe.place.presentation;

import com.xuni.cafe.common.response.SimpleResponseBody;
import com.xuni.cafe.place.application.PlaceService;
import com.xuni.cafe.place.domain.Place;
import com.xuni.cafe.place.dto.request.PlaceForm;
import com.xuni.cafe.place.dto.response.PlaceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static com.xuni.cafe.place.dto.response.PlaceAPiMessage.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/places")
    public Mono<ResponseEntity<SimpleResponseBody<Place>>> savePlace(@RequestBody Mono<PlaceForm> formMono) {
        Mono<SimpleResponseBody<Place>> bodyMono = formMono
                .flatMap(form -> placeService.enrollPlace(form))
                .map(place -> new SimpleResponseBody<>(201, ENROLL, place));

        return bodyMono.map(body -> ResponseEntity
                .created(URI.create("/places/" + body.response().getId()))
                .body(body));
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
}

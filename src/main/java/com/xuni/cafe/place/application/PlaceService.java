package com.xuni.cafe.place.application;

import com.xuni.cafe.place.domain.Place;
import com.xuni.cafe.place.domain.PlaceRepository;
import com.xuni.cafe.place.domain.PlaceTag;
import com.xuni.cafe.place.dto.request.PlaceForm;
import com.xuni.cafe.place.dto.response.PlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Mono<Place> enrollPlace(PlaceForm form) {
        return placeRepository.save(Place.builder()
                .name(form.placeName())
                .type(form.placeType())
                .address(form.address())
                .tags(createPlaceTags(form))
                .build());
    }

    private static List<PlaceTag> createPlaceTags(PlaceForm form) {
        return form.features().stream()
                .map(feature -> PlaceTag.from(feature))
                .toList();
    }

    public Mono<Place> findOne(String placeId) {
        return placeRepository.findById(placeId);
    }

    public Mono<List<PlaceResponse>> findMany() {
        return placeRepository.findAll().collectList()
                .map(places -> toPlaceResponse(places));
    }

    private static List<PlaceResponse> toPlaceResponse(List<Place> places) {
        return places.stream()
                .map(place -> PlaceResponse.of(place.getName(), place.getAddress()))
                .toList();
    }
}

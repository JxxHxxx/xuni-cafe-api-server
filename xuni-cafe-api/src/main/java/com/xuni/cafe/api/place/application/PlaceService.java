package com.xuni.cafe.api.place.application;

import com.xuni.cafe.api.place.PlaceRepository;
import com.xuni.cafe.api.place.dto.request.PlaceForm;
import com.xuni.cafe.api.place.dto.response.PlaceResponse;
import com.xuni.cafe.core.place.domain.Operation;
import com.xuni.cafe.core.place.domain.Place;
import com.xuni.cafe.core.place.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private static final int ROOM_START_NUMBER = 1;
    private static final int ADJUSTMENT_VALUE_FOR_IDX = 1;

    private final PlaceRepository placeRepository;

    public Mono<Place> enrollPlace(PlaceForm form) {
        Place place = Place.builder()
                .name(form.placeName())
                .ownerId(form.ownerId())
                .type(form.placeType())
                .address(form.address())
                .rooms(createRoom(form))
                .operation(createOperation(form))
                .build();

        place.verifyFields();

        return placeRepository.save(place);
    }

    private Operation createOperation(PlaceForm form) {
        return Operation.of(form.opening(), form.closing(), generateClosedDays(form.closedDays()));
    }

    private List<DayOfWeek> generateClosedDays(List<Integer> dayOfWeekIntValues) {
        return dayOfWeekIntValues.stream()
                .map(values -> DayOfWeek.of(values))
                .toList();
    }

    private List<Room> createRoom(PlaceForm form) {
        int roomLastNumber = form.capacities().size();
        return IntStream.rangeClosed(ROOM_START_NUMBER, roomLastNumber)
                .mapToObj(roomNumber -> Room.of(roomNumber, form.capacities().get(roomNumber - ADJUSTMENT_VALUE_FOR_IDX)))
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

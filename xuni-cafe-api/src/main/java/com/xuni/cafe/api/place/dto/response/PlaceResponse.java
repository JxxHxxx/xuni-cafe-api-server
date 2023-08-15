package com.xuni.cafe.api.place.dto.response;

import com.xuni.cafe.core.place.domain.Address;
import com.xuni.cafe.core.place.domain.Operation;
import com.xuni.cafe.core.place.domain.PlaceType;
import com.xuni.cafe.core.place.domain.Room;

import java.util.List;

public record PlaceResponse(
        String placeName,
        PlaceType placeType,
        Address address,
        List<Room> rooms,
        Operation operation
) {
    public static PlaceResponse of(String placeName, PlaceType placeType, Address address, List<Room> rooms, Operation operation) {
        return new PlaceResponse(placeName, placeType, address, rooms, operation);
    }
}
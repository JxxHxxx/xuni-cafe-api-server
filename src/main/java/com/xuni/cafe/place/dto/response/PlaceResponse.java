package com.xuni.cafe.place.dto.response;

import com.xuni.cafe.place.domain.Address;

public record PlaceResponse(
        String placeName,
        Address address
) {
    public static PlaceResponse of(String placeName, Address address) {
        return new PlaceResponse(placeName, address);
    }
}
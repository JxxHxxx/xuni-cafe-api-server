package com.xuni.cafe.place.dto.request;

import com.xuni.cafe.place.domain.Address;
import com.xuni.cafe.place.domain.Feature;
import com.xuni.cafe.place.domain.PlaceType;

import java.util.List;

public record PlaceForm(
        String placeName,
        PlaceType placeType,
        Address address,
        List<Feature> features
) {
}

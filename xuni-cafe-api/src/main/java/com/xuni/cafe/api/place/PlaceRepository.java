package com.xuni.cafe.api.place;

import com.xuni.cafe.core.place.domain.Place;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PlaceRepository extends ReactiveCrudRepository<Place, String> {

}

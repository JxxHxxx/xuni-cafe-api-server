package com.xuni.cafe.place.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Getter
@Document(collection = "places")
public class Place {

    @Id
    private String id;
    private String name;
    private Recommend recommend;
    private PlaceType type;
    private Address address;
    private List<PlaceTag> tags;

    @Builder
    public Place(String name, Recommend recommend, PlaceType type, Address address, List<PlaceTag> tags) {
        this.name = name;
        this.recommend = recommend;
        this.type = type;
        this.tags = tags;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(id, place.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

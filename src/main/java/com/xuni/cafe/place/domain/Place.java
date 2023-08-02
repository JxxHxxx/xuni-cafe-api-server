package com.xuni.cafe.place.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Document(collection = "places")
public class Place {

    @Id
    private String id;
    private String name;
    private PlaceType type;
    private Address address;

    private List<Room> rooms;
    private Operation operation;
    @Builder
    public Place(String name, PlaceType type, Address address, List<Room> rooms, Operation operation) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
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

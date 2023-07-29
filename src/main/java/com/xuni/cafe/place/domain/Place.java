package com.xuni.cafe.place.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
}

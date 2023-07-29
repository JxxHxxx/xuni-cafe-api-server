package com.xuni.cafe.place.domain;

import lombok.Getter;

@Getter
public class PlaceTag {
    private final Feature feature;
    private Integer agreement;


    private PlaceTag(Feature feature) {
        this.feature = feature;
        this.agreement = 0;
    }

    public static PlaceTag from(Feature feature) {
        return new PlaceTag(feature);
    }

    public void increaseAgreement() {
        agreement += 1;
    }

    public void decreaseAgreement() {
        agreement -= 1;
    }
}

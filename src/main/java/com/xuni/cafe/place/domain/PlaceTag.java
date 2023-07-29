package com.xuni.cafe.place.domain;

import lombok.Getter;

@Getter
public class PlaceTag {
    private final Feature feature;
    private Integer agreement;

    public PlaceTag(Feature feature, Integer agreement) {
        this.feature = feature;
        this.agreement = agreement;
    }

    public void increaseAgreement() {
        agreement += 1;
    }

    public void decreaseAgreement() {
        agreement -= 1;
    }
}

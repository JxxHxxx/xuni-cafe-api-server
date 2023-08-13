package com.xuni.cafe.core.evaluation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceTag {
    private Feature feature;
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

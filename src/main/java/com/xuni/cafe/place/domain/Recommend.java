package com.xuni.cafe.place.domain;

import lombok.Getter;

@Getter
public class Recommend {
    private final Long recommenderId;
    private final String recommender;
    private boolean flag;

    public Recommend(Long recommenderId, String recommender) {
        this.recommenderId = recommenderId;
        this.recommender = recommender;
        this.flag = true;
    }
}

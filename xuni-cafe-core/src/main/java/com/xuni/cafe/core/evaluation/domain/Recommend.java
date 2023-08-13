package com.xuni.cafe.core.evaluation.domain;

import lombok.Getter;

@Getter
public class Recommend {
    private final Long recommenderId;
    private final String recommender;
    private boolean flag;

    private Recommend(Long recommenderId, String recommender) {
        this.recommenderId = recommenderId;
        this.recommender = recommender;
        this.flag = true;
    }

    public static Recommend of(Long recommenderId, String recommender) {
        return new Recommend(recommenderId, recommender);
    }
}

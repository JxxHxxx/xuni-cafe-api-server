package com.xuni.cafe.place.domain;

import lombok.Getter;

@Getter
public enum Feature {
    MANY_OUTLET(1, "콘센트가 충분해요"), FEW_OUTLET(2,"콘센트가 부족해요"),
    MORE_NOISE(3,"조용하진 않아요"), LESS_NOISE(4,"조용해요"),
    MANY_SEAT(5,"좌석이 충분해요");

    private Integer id;
    private String desc;

    Feature(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}

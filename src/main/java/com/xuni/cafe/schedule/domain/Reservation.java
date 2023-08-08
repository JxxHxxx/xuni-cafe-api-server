package com.xuni.cafe.schedule.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Reservation {
    private Weekend weekend;
    private List<Information> informations;

    public Reservation(Weekend weekend,List<Information> informations) {
        this.weekend = weekend;
        this.informations = informations;
    }

    public static Reservation of(Weekend weekend,List<Information> informations) {
        return new Reservation(weekend, informations);
    }
}

package com.xuni.cafe.place.domain;

import lombok.Getter;

@Getter
public class Room {
    private final Integer roomNumber;
    private final Integer capacity;

    private Room(Integer roomNumber, Integer capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public static Room of(Integer roomNumber, Integer capacity) {
        return new Room(roomNumber, capacity);
    }
}

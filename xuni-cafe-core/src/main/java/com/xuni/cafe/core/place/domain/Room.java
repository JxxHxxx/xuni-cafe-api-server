package com.xuni.cafe.core.place.domain;

import lombok.Getter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) && Objects.equals(capacity, room.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, capacity);
    }
}

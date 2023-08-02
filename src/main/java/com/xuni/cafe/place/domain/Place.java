package com.xuni.cafe.place.domain;

import com.xuni.cafe.common.exception.NotPermissionException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Document(collection = "places")
public class Place {

    @Id
    private String id;
    private Long ownerId;
    private String name;
    private PlaceType type;
    private Address address;
    private List<Room> rooms;
    private Operation operation;

    @Builder
    public Place(String name, Long ownerId, PlaceType type, Address address, List<Room> rooms, Operation operation) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.ownerId = ownerId;
        this.type = type;
        this.address = address;
        this.rooms = rooms;
        this.operation = operation;
    }

    void verifyOwner(Long ownerId) {
        if (this.ownerId != ownerId) {
            throw new NotPermissionException();
        }
    }

    public void changeOpening(Long ownerId, LocalTime opening) {
        verifyOwner(ownerId);
        operation.setOpening(opening);
    }

    public void changeClosing(Long ownerId, LocalTime opening) {
        verifyOwner(ownerId);
        operation.setClosing(opening);
    }

    public void changeHolidays(Long ownerId, List<DayOfWeek> holidays){
        verifyOwner(ownerId);
        operation.setHolidays(holidays);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(id, place.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.xuni.cafe.core.place.domain;

import com.xuni.cafe.core.common.exception.NotPermissionException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

// TODO : 장소 등록 후 -> 검증을 받아야 노출되는 형태로

@Slf4j
@Getter
@Document(collection = "places")
public class Place {

    @Id
    private String id;
    private String name;
    private Long ownerId;
    private PlaceType type;
    @Indexed(unique = true)
    private final Address address;
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

    public void verifyPlaceValidation() {
        operation.validateOperationHours();
    }

    void verifyOwner(Long ownerId) {
        if (isNotOwner(ownerId)) {
            throw new NotPermissionException();
        }
    }

    private boolean isNotOwner(Long ownerId) {
        return !this.ownerId.equals(ownerId);
    }

    public void changeOperation(Long ownerId, LocalTime opening, LocalTime closing, List<DayOfWeek> holidays) {
        verifyOwner(ownerId);
        operation.setOpening(opening);
        operation.setClosing(closing);
        operation.validateOperationHours();

        operation.setHolidays(holidays);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(ownerId, place.ownerId) && Objects.equals(name, place.name) && type == place.type && Objects.equals(address, place.address) && Objects.equals(rooms, place.rooms) && Objects.equals(operation, place.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, name, type, address, rooms, operation);
    }
}

package com.xuni.cafe.place.dto.request;

import com.xuni.cafe.place.application.OperationValid;
import com.xuni.cafe.place.domain.Address;
import com.xuni.cafe.place.domain.PlaceType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalTime;
import java.util.List;

import static com.xuni.cafe.place.dto.response.PlaceAPiMessage.HOLIDAY_NOT_DUPLICATION;

@OperationValid
public record PlaceForm(
        @NotEmpty
        String placeName,
        @NotNull
        Long ownerId,
        @NotNull
        PlaceType placeType,
        @NotNull
        Address address,
        @NotNull
        LocalTime opening,
        @NotNull
        LocalTime closing,
        // valid 1 - 7 값
        @Size(max=7)
        @UniqueElements(message = HOLIDAY_NOT_DUPLICATION)
        List<Integer> closedDays,
        @NotEmpty
        List<Integer> capacities
) {
}

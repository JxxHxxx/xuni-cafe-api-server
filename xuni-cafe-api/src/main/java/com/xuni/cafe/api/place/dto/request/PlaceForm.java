package com.xuni.cafe.api.place.dto.request;

import com.xuni.cafe.api.place.application.OperationValid;
import com.xuni.cafe.core.place.domain.Address;
import com.xuni.cafe.core.place.domain.PlaceType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalTime;
import java.util.List;

import static com.xuni.cafe.api.place.dto.response.PlaceAPiMessage.HOLIDAY_NOT_DUPLICATION;

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
                // TODO : Integer Type -> DayOfWeek Type 으로 변경해야함
        List<Integer> closedDays,
        @NotEmpty
        List<Integer> capacities
) {
}

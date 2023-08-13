package com.xuni.cafe.core.schedule.domain;

import com.xuni.cafe.core.common.exception.NotPermissionException;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @See https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mapping-conversion
 */

@Getter
public class Information {
    private final Long costumerId;
    private final LocalDate reservedDate;
    private final LocalTime reservedTime;
    private final Integer reservationPersonnel;
    private boolean canceled;

    public Information(Long costumerId, LocalDate reservedDate, LocalTime reservedTime, Integer reservationPersonnel) {
        this.costumerId = costumerId;
        this.reservedDate = reservedDate;
        this.reservedTime = reservedTime;
        this.reservationPersonnel = reservationPersonnel;
        this.canceled = false;
    }

    public static Information of(Long costumerId, LocalDate reservedDate, LocalTime reservedTime, Integer reservationPersonnel) {
        return new Information(costumerId, reservedDate, reservedTime, reservationPersonnel);
    }

    public boolean isNotCanceled() {
        return !canceled;
    }

    public void cancel(Long costumerId) {
        if (this.costumerId != costumerId) {
            throw new NotPermissionException();
        }
        canceled = true;
    }
}

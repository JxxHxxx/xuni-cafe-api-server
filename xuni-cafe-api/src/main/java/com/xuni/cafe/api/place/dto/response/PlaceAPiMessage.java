package com.xuni.cafe.api.place.dto.response;

public class PlaceAPiMessage {

    private PlaceAPiMessage() {
    }

    // 2xx Response Messages
    public static final String READ_ONE = "장소 단건 조회 완료";
    public static final String READ_MANY = "장소 다건 조회 완료";
    public static final String ENROLL = "장소 등록 완료";

    // 4xx Response Messages
    public static final String  INVALID_OPERATION = "오픈 시간은 종료 시간보다 늦을 수 없습니다";
    public static final String  HOLIDAY_NOT_DUPLICATION = "같은 요일 중복해서 휴일로 지정할 수 없습니다";

    public static final String INVALID_WEEKEND_VALUES = "1 ~ 7 사이의 값을 입력해주세요. 월(0), 화(1), 수(2), 목(3), 금(4), 토(5), 일(6)";
}

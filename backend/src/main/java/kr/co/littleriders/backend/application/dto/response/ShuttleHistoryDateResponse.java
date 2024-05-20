package kr.co.littleriders.backend.application.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ShuttleHistoryDateResponse {

    private int year;
    private int month;
    private int day;

    private ShuttleHistoryDateResponse(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static ShuttleHistoryDateResponse from(LocalDateTime localDateTime){
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        return new ShuttleHistoryDateResponse(year,month,day);
    }
}

package com.cloudy.global.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class GenerateDateList {
    // 년, 월 , 일에 맞게 인덱스 데이터 파싱 하겠음.
    // method만 제작
    private final String prefix = "server-logs-";

    public List<String> getIndexBasedOnWeek(LocalDate timeIndex){
        // 특정 LocalDateTime 부터 1년 전의 주의 데이터 수행
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        List<String> logs = new ArrayList<>();

        for (int i =0; i< 52; i++){
            // time index부터 1년전 데이터 리턴
            LocalDate startWeek = timeIndex.with(DayOfWeek.MONDAY).minusWeeks(i);
            logs.add(prefix+startWeek.format(formatter));
        }
        return logs;

    }
    public List<String> getIndexBasedOnDate(LocalDate timeIndex){
        // 2. curFormatDate에서 1년전 데이터 ~~~~ Array 만들기~~
        List<String> logs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        for (int i =0; i< 365 ; i++) {
            LocalDate pastDate = timeIndex.minusDays(i);
            String tempDate = prefix +pastDate.format(formatter);
            logs.add(tempDate);
        }

        return logs;
    }
}

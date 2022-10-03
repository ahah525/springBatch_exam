package com.ll.exam.app_2022_09_22.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static class date {

        // 해당 년, 월의 마지막 일자 구하기
        public static int getEndDayOf(int year, int month) {
            String yearMonth = year + "-" + "%02d".formatted(month);

            return getEndDayOf(yearMonth);
        }

        // 년, 월의 마지막 일자 구하기
        public static int getEndDayOf(String yearMonth) {
            LocalDate convertedDate = LocalDate.parse(yearMonth + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            convertedDate = convertedDate.withDayOfMonth(
                    convertedDate.getMonth().length(convertedDate.isLeapYear()));

            return convertedDate.getDayOfMonth();
        }

        // 날짜 문자열 -> 해당 패턴의 LocalDateTime 변환
        public static LocalDateTime parse(String pattern, String dateText) {
            return LocalDateTime.parse(dateText, DateTimeFormatter.ofPattern(pattern));
        }

        // 날짜 문자열 -> 디폴트 패턴의 LocalDateTime 변환
        public static LocalDateTime parse(String dateText) {
            return parse("yyyy-MM-dd HH:mm:ss.SSSSSS", dateText);
        }
    }
}

package ua.nure.usik.SummaryTask4.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class ScheduleUtils {

    public static long durationDate(String dateFrom, String dateTo) {
        return Duration.between(LocalDateTime.parse(dateFrom.replace(" ", "T")),
                LocalDateTime.parse(dateTo.replace(" ", "T"))).toMinutes();
    }

    public static String convertIntToTime(long timeInMinutes) {
        long hours = timeInMinutes / 60;
        long minutes = timeInMinutes % 60;
        return hours + ":" + minutes;
    }
}

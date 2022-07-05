package ua.edu.sumdu.j2se.dudynskyi.ui.utils;

import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    private static final String dateFormat = "yyyy-MM-dd HH:mm";

    private static LocalDateTime getTimeFromString(String time) {
        if (time.isEmpty()) {
            return null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            return LocalDateTime.parse(time, formatter);
        }
    }

    public static LocalDateTime getTime(String timeStr, UIPrintable printUI) {
        LocalDateTime time;
        if (Validation.dateValidation(timeStr)) {
            time = DateTime.getTimeFromString(timeStr);
            if (Validation.isFuture(time)) {
                return time;
            } else {
                printUI.printInvalidPastTimeInput();
            }
        } else {
            printUI.printInvalidDateInput();
        }
        return null;
    }

    public static int getRepeatInterval(String interval, UIPrintable printUI) {
        int result = -1;
        if (interval == null || interval.isEmpty()) {
            return result;
        }
        if (Validation.defaultRepeatIntervalValidation(interval)) {
            return getRepeatIntervalFromHour(interval);
        } else if (Validation.minuteRepeatIntervalValidation(interval)) {
            return getRepeatIntervalFromMinute(interval);
        } else if (Validation.dayRepeatIntervalValidation(interval)) {
            return getRepeatIntervalFromDay(interval);
        } else {
            printUI.printInvalidRepeatInterval();
        }
        return result;
    }

    private static int getRepeatIntervalFromHour(String inputData) {
        float interval = Float.parseFloat(inputData);
        return (int) (3600 * interval);
    }

    private static int getRepeatIntervalFromMinute(String inputData) {
        String intervalStr = inputData.substring(0, inputData.length() - 1);
        float interval = Float.parseFloat(intervalStr);
        return (int) (60 * interval);
    }

    private static int getRepeatIntervalFromDay(String inputData) {
        String intervalStr = inputData.substring(0, inputData.length() - 1);
        float interval = Float.parseFloat(intervalStr);
        return (int) (86400 * interval);
    }
}

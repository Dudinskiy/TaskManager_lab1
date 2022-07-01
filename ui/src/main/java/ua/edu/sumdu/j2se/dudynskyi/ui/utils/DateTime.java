package ua.edu.sumdu.j2se.dudynskyi.ui.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    public static LocalDateTime getTimeFromString(String time) {
        if (time.isEmpty()) {
            return null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(time, formatter);
        }
    }

    public static LocalDateTime inputTimeToCreate(BufferedReader reader) throws IOException {
        String timeStr;
        LocalDateTime time;
        while (true) {
            timeStr = reader.readLine();
            if (Validation.dateValidation(timeStr)) {
                time = DateTime.getTimeFromString(timeStr);
                if (Validation.isNotPast(time)) {
                    break;
                } else {
                    System.out.println("You have entered a past date. Enter the date");
                }
            } else {
                System.out.println("Invalid date value. Enter the date");
            }
        }
        return time;
    }

    public static LocalDateTime inputTimeToSet(BufferedReader reader) throws IOException {
        String timeStr;
        LocalDateTime time;
        while (true) {
            timeStr = reader.readLine();
            if(timeStr.isEmpty()){
                return null;
            }
            if (Validation.dateValidation(timeStr)) {
                time = DateTime.getTimeFromString(timeStr);
                if (Validation.isNotPast(time)) {
                    break;
                } else {
                    System.out.println("You have entered a past date. Enter the date");
                }
            } else {
                System.out.println("Invalid date value. Enter the date");
            }
        }
        return time;
    }
}

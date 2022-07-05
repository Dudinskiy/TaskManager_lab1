package ua.edu.sumdu.j2se.dudynskyi.ui.utils;

import ua.edu.sumdu.j2se.dudynskyi.ui.enums.Languages;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean yesNoValidation(String choice) {
        return UserInput.YES.equals(choice)
                || UserInput.NO.equals(choice);
    }

    public static boolean cancelValidation(String cancel) {
        return UserInput.CANCEL.equals(cancel);
    }

    public static boolean dateValidation(String date) {
        return dateFullFormat(date) || dateShortFormat(date);
    }

    public static boolean languageValidation(String language) {
        return Languages.ENG.toString().equalsIgnoreCase(language) ||
                Languages.UKR.toString().equalsIgnoreCase(language) ||
                Languages.RUS.toString().equalsIgnoreCase(language);
    }

    private static boolean dateFullFormat(String date) {
        String regDate = "^20[2-9][2-9]-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1]) " +
                "(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|00)$";

        Pattern pattern = Pattern.compile(regDate);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    private static boolean dateShortFormat(String date) {
        String regDate = "^(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1]) " +
                "(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|00)$";

        Pattern pattern = Pattern.compile(regDate);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static boolean isFuture(LocalDateTime date) {
        LocalDateTime current = LocalDateTime.now().withSecond(0).withNano(0);
        return current.isBefore(date);
    }

    public static boolean isFarFuture(LocalDateTime date) {
        return false;
    }

    public static boolean taskNumberValidation(String number) {
        String regNumber = "^(-[1-9][0-9]*|0|[1-9][0-9]*)$";

        Pattern pattern = Pattern.compile(regNumber);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public static boolean defaultRepeatIntervalValidation(String interval) {
        String regNumber = "^([1-9][0-9]*\\.[0-9]{1,2}|0\\.[0-9]{1,2}|[1-9][0-9]{1,4}|[1-9])$";

        Pattern pattern = Pattern.compile(regNumber);
        Matcher matcher = pattern.matcher(interval);
        return matcher.matches();
    }

    public static boolean minuteRepeatIntervalValidation(String interval) {
        String regNumber = "^([1-9][0-9]*\\.[0-9]{1,2}|0\\.[0-9]{1,2}|[1-9][0-9]{1,6}|[1-9])[m,M]$";

        Pattern pattern = Pattern.compile(regNumber);
        Matcher matcher = pattern.matcher(interval);
        return matcher.matches();
    }

    public static boolean dayRepeatIntervalValidation(String interval) {
        String regNumber = "^([1-9][0-9]*\\.[0-9]{1,2}|0\\.[0-9]{1,2}|[1-9][0-9]{1,3}|[1-9])[d,D]$";

        Pattern pattern = Pattern.compile(regNumber);
        Matcher matcher = pattern.matcher(interval);
        return matcher.matches();
    }
}

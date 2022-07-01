package ua.edu.sumdu.j2se.dudynskyi.ui.utils;

import ua.edu.sumdu.j2se.dudynskyi.ui.enums.Choice;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean yncValidation(String choice) {
        return Choice.Y.toString().equalsIgnoreCase(choice)
                || Choice.N.toString().equalsIgnoreCase(choice)
                || Choice.C.toString().equalsIgnoreCase(choice);
    }

    public static boolean ynValidation(String choice) {
        return Choice.Y.toString().equalsIgnoreCase(choice)
                || Choice.N.toString().equalsIgnoreCase(choice);
    }

    public static boolean cValidation(String cancel) {
        return Choice.C.toString().equalsIgnoreCase(cancel);
    }

    public static boolean dateValidation(String date) {
        return dateFullFormat(date) || dateShortFormat(date);
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

    public static boolean isNotPast(LocalDateTime date) {
        LocalDateTime current = LocalDateTime.now().withSecond(0).withNano(0);
        return current.isBefore(date);
    }

    public static boolean taskNumberValidation(String number){
        String regNumber = "^(0|[1-9][0-9]*)$";

        Pattern pattern = Pattern.compile(regNumber);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}

package ua.edu.sumdu.j2se.dudynskyi.ui.unit.utils;

import org.junit.Assert;
import org.junit.Test;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.Validation;

import java.time.LocalDateTime;

public class ValidationTest {

    private static final String YES = "+";
    private static final String NO = "-";
    private static final String CANCEL = " ";
    private static final String wrong_1 = "0";
    private static final String wrong_2 = "B";

    private static final String fullDateRight = "2022-01-01 12:00";
    private static final String fullDateRight_1 = "2022-01-01 00:00";
    private static final String fullDateRight_2 = "2022-01-01 23:59";
    private static final String shortDateRight = "01-01 12:00";
    private static final String fullDateWrong_1 = "1922-01-01 12:00";
    private static final String fullDateWrong_2 = "2122-01-01 12:00";
    private static final String fullDateWrong_3 = "2122-01-01 12:00";
    private static final String fullDateWrong_4 = "1022-01-01 12:00";
    private static final String fullDateWrong_5 = "2022-00-01 12:00";
    private static final String fullDateWrong_6 = "2022-13-01 12:00";
    private static final String fullDateWrong_7 = "2022-12-00 12:00";
    private static final String fullDateWrong_8 = "2022-12-32 12:00";
    private static final String fullDateWrong_9 = "2022-12-32 24:00";
    private static final String fullDateWrong_10 = "2022-12-32 23:60";

    private static final LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
    private static final LocalDateTime past = now.minusHours(1);
    private static final LocalDateTime future = now.plusHours(1);

    private static final String hourIntervalRightMin = "0.01";
    private static final String hourIntervalRightMax = "99999.99";
    private static final String hourIntervalRight_1 = "1";
    private static final String hourIntervalRight_2 = "10";
    private static final String hourIntervalWrong_1 = "0";
    private static final String hourIntervalWrong_2 = "00.1";
    private static final String hourIntervalWrong_3 = "0.111";
    private static final String hourIntervalWrong_4 = "100000";
    private static final String hourIntervalWrong_5 = "-1";
    private static final String hourIntervalWrong_6 = "tyu1";

    private static final String minuteIntervalRightMin_1 = "0.01m";
    private static final String minuteIntervalRightMin_2 = "0.01M";
    private static final String minuteIntervalRightMax_1 = "9999999.99m";
    private static final String minuteIntervalRightMax_2 = "9999999.99M";
    private static final String minuteIntervalRight_1 = "1m";
    private static final String minuteIntervalRight_2 = "10M";
    private static final String minuteIntervalWrong_1 = "0.01f";
    private static final String minuteIntervalWrong_2 = "0.01";
    private static final String minuteIntervalWrong_3 = "0.001m";
    private static final String minuteIntervalWrong_4 = "10000000M";
    private static final String minuteIntervalWrong_5 = "-1m";
    private static final String minuteIntervalWrong_6 = "0m";
    private static final String minuteIntervalWrong_7 = "qw0w";

    private static final String dayIntervalRightMin_1 = "0.01d";
    private static final String dayIntervalRightMin_2 = "0.01D";
    private static final String dayIntervalRightMax_1 = "9999.99d";
    private static final String dayIntervalRightMax_2 = "9999.99D";
    private static final String dayIntervalRight_1 = "1d";
    private static final String dayIntervalRight_2 = "10D";
    private static final String dayIntervalWrong_1 = "0.01m";
    private static final String dayIntervalWrong_2 = "0.01";
    private static final String dayIntervalWrong_3 = "0.001d";
    private static final String dayIntervalWrong_4 = "10000D";
    private static final String dayIntervalWrong_5 = "-1d";
    private static final String dayIntervalWrong_6 = "0d";
    private static final String dayIntervalWrong_7 = "tyut";


    @Test
    public void yesNoValidationTest() {
        Assert.assertTrue(Validation.yesNoValidation(YES));
        Assert.assertTrue(Validation.yesNoValidation(NO));

        Assert.assertFalse(Validation.yesNoValidation(wrong_1));
        Assert.assertFalse(Validation.yesNoValidation(wrong_2));
    }

    @Test
    public void cancelValidationTest() {
        Assert.assertTrue(Validation.cancelValidation(CANCEL));

        Assert.assertFalse(Validation.cancelValidation(wrong_1));
        Assert.assertFalse(Validation.cancelValidation(wrong_2));
    }

    @Test
    public void dateValidationTest() {
        Assert.assertTrue(Validation.dateValidation(fullDateRight));
        Assert.assertTrue(Validation.dateValidation(fullDateRight_1));
        Assert.assertTrue(Validation.dateValidation(fullDateRight_2));
        Assert.assertTrue(Validation.dateValidation(shortDateRight));

        Assert.assertFalse(Validation.dateValidation(fullDateWrong_1));
        Assert.assertFalse(Validation.dateValidation(fullDateWrong_2));
        Assert.assertFalse(Validation.dateValidation(fullDateWrong_3));
        Assert.assertFalse(Validation.dateValidation(fullDateWrong_4));
        Assert.assertFalse(Validation.dateValidation(fullDateWrong_5));
        Assert.assertFalse(Validation.dateValidation(fullDateWrong_6));
        Assert.assertFalse(Validation.dateValidation(fullDateWrong_7));
        Assert.assertFalse(Validation.dateValidation(fullDateWrong_8));
        Assert.assertFalse(Validation.dateValidation(fullDateWrong_9));
        Assert.assertFalse(Validation.dateValidation(fullDateWrong_10));
        Assert.assertFalse(Validation.dateValidation(wrong_2));
    }

    @Test
    public void isNotPastTest() {
        Assert.assertTrue(Validation.isFuture(future));
        Assert.assertFalse(Validation.isFuture(now));
        Assert.assertFalse(Validation.isFuture(past));
    }

    @Test
    public void defaultRepeatIntervalValidationTest() {
        Assert.assertTrue(Validation.defaultRepeatIntervalValidation(hourIntervalRightMin));
        Assert.assertTrue(Validation.defaultRepeatIntervalValidation(hourIntervalRightMax));
        Assert.assertTrue(Validation.defaultRepeatIntervalValidation(hourIntervalRight_1));
        Assert.assertTrue(Validation.defaultRepeatIntervalValidation(hourIntervalRight_2));

        Assert.assertFalse(Validation.defaultRepeatIntervalValidation(hourIntervalWrong_1));
        Assert.assertFalse(Validation.defaultRepeatIntervalValidation(hourIntervalWrong_2));
        Assert.assertFalse(Validation.defaultRepeatIntervalValidation(hourIntervalWrong_3));
        Assert.assertFalse(Validation.defaultRepeatIntervalValidation(hourIntervalWrong_4));
        Assert.assertFalse(Validation.defaultRepeatIntervalValidation(hourIntervalWrong_5));
        Assert.assertFalse(Validation.defaultRepeatIntervalValidation(hourIntervalWrong_6));
    }

    @Test
    public void minuteRepeatIntervalValidationTest() {
        Assert.assertTrue(Validation.minuteRepeatIntervalValidation(minuteIntervalRightMin_1));
        Assert.assertTrue(Validation.minuteRepeatIntervalValidation(minuteIntervalRightMin_2));
        Assert.assertTrue(Validation.minuteRepeatIntervalValidation(minuteIntervalRightMax_1));
        Assert.assertTrue(Validation.minuteRepeatIntervalValidation(minuteIntervalRightMax_2));
        Assert.assertTrue(Validation.minuteRepeatIntervalValidation(minuteIntervalRight_1));
        Assert.assertTrue(Validation.minuteRepeatIntervalValidation(minuteIntervalRight_2));

        Assert.assertFalse(Validation.minuteRepeatIntervalValidation(minuteIntervalWrong_1));
        Assert.assertFalse(Validation.minuteRepeatIntervalValidation(minuteIntervalWrong_2));
        Assert.assertFalse(Validation.minuteRepeatIntervalValidation(minuteIntervalWrong_3));
        Assert.assertFalse(Validation.minuteRepeatIntervalValidation(minuteIntervalWrong_4));
        Assert.assertFalse(Validation.minuteRepeatIntervalValidation(minuteIntervalWrong_5));
        Assert.assertFalse(Validation.minuteRepeatIntervalValidation(minuteIntervalWrong_6));
        Assert.assertFalse(Validation.minuteRepeatIntervalValidation(minuteIntervalWrong_7));
    }

    @Test
    public void dayRepeatIntervalValidation() {
        Assert.assertTrue(Validation.dayRepeatIntervalValidation(dayIntervalRightMin_1));
        Assert.assertTrue(Validation.dayRepeatIntervalValidation(dayIntervalRightMin_2));
        Assert.assertTrue(Validation.dayRepeatIntervalValidation(dayIntervalRightMax_1));
        Assert.assertTrue(Validation.dayRepeatIntervalValidation(dayIntervalRightMax_2));
        Assert.assertTrue(Validation.dayRepeatIntervalValidation(dayIntervalRight_1));
        Assert.assertTrue(Validation.dayRepeatIntervalValidation(dayIntervalRight_2));

        Assert.assertFalse(Validation.dayRepeatIntervalValidation(dayIntervalWrong_1));
        Assert.assertFalse(Validation.dayRepeatIntervalValidation(dayIntervalWrong_2));
        Assert.assertFalse(Validation.dayRepeatIntervalValidation(dayIntervalWrong_3));
        Assert.assertFalse(Validation.dayRepeatIntervalValidation(dayIntervalWrong_4));
        Assert.assertFalse(Validation.dayRepeatIntervalValidation(dayIntervalWrong_5));
        Assert.assertFalse(Validation.dayRepeatIntervalValidation(dayIntervalWrong_6));
        Assert.assertFalse(Validation.dayRepeatIntervalValidation(dayIntervalWrong_7));
    }
}

package ua.edu.sumdu.j2se.dudynskyi.ui.unit.utils;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.PrintsEng;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.DateTime;

import java.time.LocalDateTime;

public class DateTimeTest {

    private static final UIPrintable printUI = new PrintsEng();

    private static final LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
    private static final LocalDateTime past = now.minusHours(1);
    private static final LocalDateTime future = now.plusHours(1);
    private static final String fullDateWrong = "1022-01-01 12:00";
    private static final String empty = "";
    private static final String stringNow = getStringFromDate(now);
    private static final String stringPast = getStringFromDate(past);
    private static final String stringFuture = getStringFromDate(future);

    private static final String hourIntervalRight_1 = "10";
    private static final int hourIntervalRightToSeconds_1 = 36000;
    private static final String minuteIntervalRight_1 = "10m";
    private static final int minuteIntervalRightToSeconds_1 = 600;
    private static final String dayIntervalRight_1 = "10d";
    private static final int dayIntervalRightToSeconds_1 = 864000;
    private static final String hourIntervalWrong_1 = "0";
    private static final int failResult = -1;

    @Test
    public void getTimeTest() {
        Assert.assertEquals(DateTime.getTime(stringFuture, printUI), future);
        Assert.assertNull(DateTime.getTime(stringPast, printUI));
        Assert.assertNull(DateTime.getTime(stringNow, printUI));
        Assert.assertNull(DateTime.getTime(fullDateWrong, printUI));
        Assert.assertNull(DateTime.getTime(empty, printUI));
    }

    @Test
    public void getRepeatIntervalTest() {
        Assert.assertEquals(DateTime.getRepeatInterval(hourIntervalRight_1, printUI)
                , hourIntervalRightToSeconds_1);
        Assert.assertEquals(DateTime.getRepeatInterval(minuteIntervalRight_1, printUI)
                , minuteIntervalRightToSeconds_1);
        Assert.assertEquals(DateTime.getRepeatInterval(dayIntervalRight_1, printUI)
                , dayIntervalRightToSeconds_1);
        Assert.assertEquals(DateTime.getRepeatInterval(hourIntervalWrong_1, printUI)
                , failResult);
    }

    private static String getStringFromDate(LocalDateTime time) {
        return time.toString().replace("T", " ");
    }
}

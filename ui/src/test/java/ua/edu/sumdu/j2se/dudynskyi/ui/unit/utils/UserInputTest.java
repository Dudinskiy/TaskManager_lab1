package ua.edu.sumdu.j2se.dudynskyi.ui.unit.utils;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.ArrayTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.PrintsEng;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.UserInput;

import java.io.IOException;
import java.time.LocalDateTime;


public class UserInputTest {

    public static final Logger logger = LoggerFactory.getLogger(UserInputTest.class);
    private static final UIPrintable printUI = new PrintsEng();

    private static final LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
    private static final AbstractTaskList taskList = getTaskList();
    private static final AbstractTaskList emptyTaskList = new ArrayTaskList();
    private static final String[] stringTaskNumbers = getStringTaskNumbers();
    private static final int[] taskNumbers = getTaskNumbers();
    private static final String taskNumberOne = "1";
    private static final String taskNumberWrong_1 = "0";
    private static final String taskNumberWrong_2 = Integer.toString(taskList.size() + 1);
    private static final String notNumber = "w";
    private static final int failResult = -1;


    @Test
    public void inputTaskNumberTest() {
        for (int i = 0; i < stringTaskNumbers.length; i++) {
            Assert.assertEquals(UserInput.inputTaskNumber(stringTaskNumbers[i], taskList, printUI)
                    , taskNumbers[i]);
        }
        Assert.assertEquals(UserInput.inputTaskNumber(taskNumberOne, null, printUI), failResult);
        Assert.assertEquals(UserInput.inputTaskNumber(taskNumberOne, emptyTaskList, printUI), failResult);
        Assert.assertEquals(UserInput.inputTaskNumber(taskNumberWrong_1, taskList, printUI), failResult);
        Assert.assertEquals(UserInput.inputTaskNumber(taskNumberWrong_2, taskList, printUI), failResult);
        Assert.assertEquals(UserInput.inputTaskNumber(notNumber, taskList, printUI), failResult);
    }

    private static AbstractTaskList getTaskList() {
        AbstractTaskList result = new ArrayTaskList();
        Task task_1 = new Task("t", now);
        result.add(task_1);
        result.add(task_1);
        result.add(task_1);
        return result;
    }

    private static String[] getStringTaskNumbers() {
        String[] result = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            result[i] = Integer.toString(i + 1);
        }
        return result;
    }

    private static int[] getTaskNumbers() {
        int[] result = new int[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            result[i] = i + 1;
        }
        return result;
    }
}

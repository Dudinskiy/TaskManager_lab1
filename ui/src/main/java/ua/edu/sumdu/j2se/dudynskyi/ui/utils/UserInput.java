package ua.edu.sumdu.j2se.dudynskyi.ui.utils;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;

public class UserInput {

    public static int inputActionNumber(BufferedReader reader) throws IOException {
        int result;
        while (true) {
            String taskNumberStr = reader.readLine();
            if (Validation.taskNumberValidation(taskNumberStr)) {
                result = Integer.parseInt(taskNumberStr);
                if (result > 0 && result <= 7) {
                    break;
                } else {
                    System.out.println("Incorrect value. There is no action with this number");
                }
            } else {
                System.out.println("Incorrect value. The value must be numeric");
            }
        }
        return result;
    }

    public static int inputTaskNumber(BufferedReader reader, AbstractTaskList taskList) throws IOException {
        int result;
        while (true) {
            String taskNumberStr = reader.readLine();
            if (Validation.taskNumberValidation(taskNumberStr)) {
                result = Integer.parseInt(taskNumberStr);
                if (result > 0 && result <= taskList.size()) {
                    break;
                } else {
                    System.out.println("Incorrect value. There is no task with this number");
                }
            } else {
                System.out.println("Incorrect value. The value must be numeric");
            }
        }
        return result;
    }
}

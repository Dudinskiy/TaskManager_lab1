package ua.edu.sumdu.j2se.dudynskyi.ui.utils;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.ui.exceptions.InputDataException;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;

import java.io.BufferedReader;
import java.io.IOException;

public class UserInput {

    public static final String YES = "+";
    public static final String NO = "-";
    public static final String CANCEL = " ";

    private UserInput() {
    }

    public static int inputActionNumber(BufferedReader reader
            , UIPrintable printUI) throws InputDataException {
        int result;
        String userInput;
        while (true) {
            try {
                userInput = reader.readLine();
                if (Validation.taskNumberValidation(userInput)) {
                    result = Integer.parseInt(userInput);
                    if (result > 0 && result <= 8) {
                        break;
                    } else {
                        printUI.printInvalidActionNumber();
                    }
                } else {
                    printUI.printInvalidNotNumeric();
                }
            } catch (IOException e) {
                throw new InputDataException("Exception when entering action number", e);
            }
        }
        return result;
    }

    public static int inputTaskNumber(String number
            , AbstractTaskList taskList, UIPrintable printUI) {
        int result = -1;
        if (taskList == null || taskList.size() == 0) {
            return result;
        }
        int inputNumber;
        if (Validation.taskNumberValidation(number)) {
            inputNumber = Integer.parseInt(number);
            if (inputNumber > 0 && inputNumber <= taskList.size()) {
                result = inputNumber;
                return result;
            } else {
                printUI.printInvalidTaskNumber();
            }
        } else {
            printUI.printInvalidNotNumeric();
        }
        return result;
    }
}

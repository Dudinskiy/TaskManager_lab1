package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.UserInput;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveTaskView extends AbstractView {

    private static final Logger logger = LoggerFactory.getLogger(RemoveTaskView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public RemoveTaskView(UIPrintable printUI) {
        super(printUI);
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            printUI.printTaskListIsEmpty();
            return Controller.MAIN_MENU_ACTION;
        }
        int taskNumber;
        boolean isRemove;
        String userInput;

        printUI.printRemoveTaskHeadPhase();
        printUI.printTaskList(taskList);

        try {
            while (true) {
                userInput = reader.readLine();
                if (Validation.cancelValidation(userInput)) {
                    printUI.printCancel();
                    return Controller.MAIN_MENU_ACTION;
                } else {
                    taskNumber = UserInput.inputTaskNumber(userInput, taskList, printUI);
                    if (taskNumber > 0) {
                        break;
                    }
                }
            }
            Task task = taskList.getTask(taskNumber - 1);
            isRemove = taskList.remove(task);
            if (isRemove) {
                printUI.printTaskRemoved();
                logger.info("{} has removed", task);
            } else {
                printUI.printIsWrongTryAgain();
            }
        } catch (IOException e) {
            logger.error("User input error", e);
            printUI.printIsWrongTryAgain();
        }
        System.out.println("===================");
        return Controller.MAIN_MENU_ACTION;
    }
}

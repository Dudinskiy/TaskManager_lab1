package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.DateTime;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class AddTaskView extends AbstractView {

    private static final Logger logger = LoggerFactory.getLogger(AddTaskView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public AddTaskView(UIPrintable printUI) {
        super(printUI);
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        printUI.printAddTaskHeadPhrase();

        String title;
        LocalDateTime time;
        LocalDateTime startTime;
        LocalDateTime endTime;
        int repeatInterval;
        Task task;
        String inputTitle;
        String selectTaskType;
        String inputTime;
        String inputStartTime;
        String inputEndTime;
        String inputRepeatInterval;

        try {
            while (true) {
                selectTaskType = reader.readLine();
                if (Validation.cancelValidation(selectTaskType)) {
                    printUI.printCancel();
                    return Controller.MAIN_MENU_ACTION;
                }
                if (Validation.yesNoValidation(selectTaskType)) {
                    break;
                } else {
                    printUI.printInvalidYesNoInput();
                }
            }

            printUI.printInputTaskTitle();
            inputTitle = reader.readLine();
            if (Validation.cancelValidation(inputTitle)) {
                printUI.printCancel();
                return Controller.MAIN_MENU_ACTION;
            } else {
                title = inputTitle;
            }

            if (Validation.yesValidation(selectTaskType)) {
                printUI.printInputTime();
                while (true) {
                    inputTime = reader.readLine();
                    if (Validation.cancelValidation(inputTime)) {
                        printUI.printCancel();
                        return Controller.MAIN_MENU_ACTION;
                    } else {
                        time = DateTime.getTime(inputTime, printUI);
                        if (time != null) {
                            break;
                        }
                    }
                }
                task = new Task(title, time);
            } else {
                printUI.printInputStartTime();
                while (true) {
                    inputStartTime = reader.readLine();
                    if (Validation.cancelValidation(inputStartTime)) {
                        printUI.printCancel();
                        return Controller.MAIN_MENU_ACTION;
                    } else {
                        startTime = DateTime.getTime(inputStartTime, printUI);
                        if (startTime != null) {
                            break;
                        }
                    }
                }
                printUI.printInputEndTime();
                while (true) {
                    inputEndTime = reader.readLine();
                    if (Validation.cancelValidation(inputEndTime)) {
                        printUI.printCancel();
                        return Controller.MAIN_MENU_ACTION;
                    } else {
                        endTime = DateTime.getTime(inputEndTime, printUI);
                        if (endTime != null) {
                            break;
                        }
                    }
                }
                while (startTime.isAfter(endTime) || startTime.equals(endTime)) {
                    printUI.printEndTimeBiggerStartTime();
                    while (true) {
                        inputEndTime = reader.readLine();
                        if (Validation.cancelValidation(inputEndTime)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            endTime = DateTime.getTime(inputEndTime, printUI);
                            if (endTime != null) {
                                break;
                            }
                        }
                    }
                }
                printUI.printInputRepeatInterval();
                while (true) {
                    inputRepeatInterval = reader.readLine();
                    if (Validation.cancelValidation(inputRepeatInterval)) {
                        printUI.printCancel();
                        return Controller.MAIN_MENU_ACTION;
                    } else {
                        repeatInterval = DateTime.getRepeatInterval(inputRepeatInterval, printUI);
                        if (repeatInterval > 0) {
                            break;
                        }
                    }
                }
                task = new Task(title, startTime, endTime, repeatInterval);
            }
            task.setActive(true);
            taskList.add(task);
            printUI.printNewTaskAdded();
            logger.info("{} has added", task);

        } catch (IOException e) {
            logger.error("User input error", e);
            printUI.printIsWrongTryAgain();
        }
        System.out.println("===================");

        return Controller.MAIN_MENU_ACTION;
    }
}

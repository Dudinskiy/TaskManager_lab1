package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.DateTime;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.UserInput;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class SetTaskView extends AbstractView {

    private static final Logger logger = LoggerFactory.getLogger(SetTaskView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public SetTaskView(UIPrintable printUI) {
        super(printUI);
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            printUI.printTaskListIsEmpty();
            return Controller.MAIN_MENU_ACTION;
        }

        String userInput;
        String title;
        Task task;
        LocalDateTime time;
        LocalDateTime startTime;
        LocalDateTime endTime;
        int taskNumber;
        int repeatInterval;
        boolean isChanged = false;

        printUI.printSelectTaskForSet();
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
            task = taskList.getTask(taskNumber - 1);
            System.out.println(task + "\n");
            printUI.printSetTaskHeadPhrase();

            while (true) {
                userInput = reader.readLine();
                if (Validation.cancelValidation(userInput)) {
                    printUI.printCancel();
                    return Controller.MAIN_MENU_ACTION;
                }
                if (Validation.yesNoValidation(userInput)) {
                    break;
                } else {
                    printUI.printInvalidYesNoInput();
                }
            }
            printUI.printInputTaskTitle();
            userInput = reader.readLine();
            if (Validation.cancelValidation(userInput)) {
                printUI.printCancel();
                return Controller.MAIN_MENU_ACTION;
            } else {
                if (!userInput.isEmpty()) {
                    title = userInput;
                    isChanged = true;
                } else {
                    title = task.getTitle();
                }
            }
            if (Validation.yesNoValidation(userInput)) {
                if (task.isRepeated()) {
                    printUI.printInputNewTime();
                    while (true) {
                        userInput = reader.readLine();
                        if (Validation.cancelValidation(userInput)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            time = DateTime.getTime(userInput, printUI);
                            if (time == null && userInput.isEmpty()) {
                                break;
                            }
                            if (time != null) {
                                isChanged = true;
                                break;
                            }
                        }
                    }
                    if (time == null) {
                        time = task.getEndTime();
                    }
                    task.setTime(time);

                } else {
                    printUI.printInputNewStartTime();
                    while (true) {
                        userInput = reader.readLine();
                        if (Validation.cancelValidation(userInput)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            startTime = DateTime.getTime(userInput, printUI);
                            if (startTime == null && userInput.isEmpty()) {
                                break;
                            }
                            if (startTime != null) {
                                isChanged = true;
                                break;
                            }
                        }
                    }
                    printUI.printInputNewEndTime();
                    while (true) {
                        userInput = reader.readLine();
                        if (Validation.cancelValidation(userInput)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            endTime = DateTime.getTime(userInput, printUI);
                            if (endTime == null && userInput.isEmpty()) {
                                break;
                            }
                            if (endTime != null) {
                                isChanged = true;
                                break;
                            }
                        }
                    }
                    printUI.printInputRepeatInterval();
                    String interval = reader.readLine();
                    if (!interval.isEmpty()) {
                        repeatInterval = Integer.parseInt(interval);
                    } else {
                        repeatInterval = task.getRepeatInterval();
                    }
                    if (startTime == null) {
                        startTime = task.getStartTime();
                    }
                    if (endTime == null) {
                        endTime = task.getEndTime();
                    }
                    task.setTime(startTime, endTime, repeatInterval);
                }
            } else {
                if (task.isRepeated()) {
                    printUI.printInputNewStartTime();
                    while (true) {
                        userInput = reader.readLine();
                        if (Validation.cancelValidation(userInput)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            startTime = DateTime.getTime(userInput, printUI);
                            if (startTime == null && userInput.isEmpty()) {
                                break;
                            }
                            if (startTime != null) {
                                isChanged = true;
                                break;
                            }
                        }
                    }
                    printUI.printInputNewEndTime();
                    while (true) {
                        userInput = reader.readLine();
                        if (Validation.cancelValidation(userInput)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            endTime = DateTime.getTime(userInput, printUI);
                            if (endTime == null && userInput.isEmpty()) {
                                break;
                            }
                            if (endTime != null) {
                                isChanged = true;
                                break;
                            }
                        }
                    }
                    if (startTime != null && endTime != null) {
                        while (startTime.isAfter(endTime) || startTime.equals(endTime)) {
                            printUI.printEndTimeBiggerStartTime();
                            while (true) {
                                userInput = reader.readLine();
                                if (Validation.cancelValidation(userInput)) {
                                    printUI.printCancel();
                                    return Controller.MAIN_MENU_ACTION;
                                } else {
                                    endTime = DateTime.getTime(userInput, printUI);
                                    if (endTime != null) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    printUI.printInputRepeatInterval();
                    while (true) {
                        userInput = reader.readLine();
                        if (Validation.cancelValidation(userInput)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            repeatInterval = DateTime.getRepeatInterval(userInput, printUI);
                            if (repeatInterval < 0 && userInput.isEmpty()) {
                                break;
                            }
                            if (repeatInterval > 0) {
                                break;
                            }
                        }
                    }
                    if (repeatInterval < 0) {
                        repeatInterval = task.getRepeatInterval();
                    }
                    if (startTime == null) {
                        startTime = task.getStartTime();
                    }
                    if (endTime == null) {
                        endTime = task.getEndTime();
                    }

                    task.setTime(startTime, endTime, repeatInterval);
                } else {
                    printUI.printInputNewTime();
                    while (true) {
                        userInput = reader.readLine();
                        if (Validation.cancelValidation(userInput)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            time = DateTime.getTime(userInput, printUI);
                            if (time == null && userInput.isEmpty()) {
                                break;
                            }
                            if (time != null) {
                                isChanged = true;
                                break;
                            }
                        }
                    }
                    if (time == null) {
                        time = task.getEndTime();
                    }
                    task.setTime(time);
                }
            }
            task.setTitle(title);
            if (isChanged) {
                logger.info("The task {} has changed", task.getTitle());
            }

        } catch (IOException e) {
            logger.error("User input error", e);
            printUI.printIsWrongTryAgain();
        }
        System.out.println("===================");
        return Controller.MAIN_MENU_ACTION;
    }
}

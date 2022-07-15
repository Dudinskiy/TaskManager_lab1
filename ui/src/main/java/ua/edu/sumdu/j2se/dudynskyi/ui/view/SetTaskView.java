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

        String inputTitle;
        String inputTaskNumber;
        String inputChangeType;
        String inputTime;
        String inputStartTime;
        String inputEndTime;
        String inputRepeatInterval;

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
            //Выбор задачи из списка
            while (true) {
                inputTaskNumber = reader.readLine();
                if (Validation.cancelValidation(inputTaskNumber)) {
                    printUI.printCancel();
                    return Controller.MAIN_MENU_ACTION;
                } else {
                    taskNumber = UserInput.inputTaskNumber(inputTaskNumber, taskList, printUI);
                    if (taskNumber > 0) {
                        break;
                    }
                }
            }
            task = taskList.getTask(taskNumber - 1);
            System.out.println(task + "\n");
            printUI.printSetTaskHeadPhrase();

            //Выбор: меняем тип задачи или нет
            while (true) {
                inputChangeType = reader.readLine();
                if (Validation.cancelValidation(inputChangeType)) {
                    printUI.printCancel();
                    return Controller.MAIN_MENU_ACTION;
                }
                if (Validation.yesNoValidation(inputChangeType)) {
                    break;
                } else {
                    printUI.printInvalidYesNoInput();
                }
            }

            //Меняем название задачи
            printUI.printInputTaskTitle();
            inputTitle = reader.readLine();
            if (Validation.cancelValidation(inputTitle)) {
                printUI.printCancel();
                return Controller.MAIN_MENU_ACTION;
            } else {
                if (!inputTitle.isEmpty()) {
                    title = inputTitle;
                    isChanged = true;
                } else {
                    title = task.getTitle();
                }
            }
            //Тип задачи меняется
            if (Validation.yesValidation(inputChangeType)) {
                if (task.isRepeated()) {
                    printUI.printInputNewTime();
                    while (true) {
                        inputTime = reader.readLine();
                        if (Validation.cancelValidation(inputTime)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            time = DateTime.getTime(inputTime, printUI);
                            if (time == null && inputTime.isEmpty()) {
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
                        inputStartTime = reader.readLine();
                        if (Validation.cancelValidation(inputStartTime)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            startTime = DateTime.getTime(inputStartTime, printUI);
                            if (startTime == null && inputStartTime.isEmpty()) {
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
                        inputEndTime = reader.readLine();
                        if (Validation.cancelValidation(inputEndTime)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            endTime = DateTime.getTime(inputEndTime, printUI);
                            if (endTime == null && inputEndTime.isEmpty()) {
                                break;
                            }
                            if (endTime != null) {
                                isChanged = true;
                                break;
                            }
                        }
                    }
                    printUI.printInputRepeatInterval();
                    inputRepeatInterval = reader.readLine();
                    if (!inputRepeatInterval.isEmpty()) {
                        repeatInterval = Integer.parseInt(inputRepeatInterval);
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

            //Тип задачи не меняется
            } else {
                if (task.isRepeated()) {
                    printUI.printInputNewStartTime();
                    while (true) {
                        inputStartTime = reader.readLine();
                        if (Validation.cancelValidation(inputStartTime)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            startTime = DateTime.getTime(inputStartTime, printUI);
                            if (startTime == null && inputStartTime.isEmpty()) {
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
                        inputEndTime = reader.readLine();
                        if (Validation.cancelValidation(inputEndTime)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            endTime = DateTime.getTime(inputEndTime, printUI);
                            if (endTime == null && inputEndTime.isEmpty()) {
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
                    }
                    printUI.printInputRepeatInterval();
                    while (true) {
                        inputRepeatInterval = reader.readLine();
                        if (Validation.cancelValidation(inputRepeatInterval)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            repeatInterval = DateTime.getRepeatInterval(inputRepeatInterval, printUI);
                            if (repeatInterval < 0 && inputRepeatInterval.isEmpty()) {
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
                        inputTime = reader.readLine();
                        if (Validation.cancelValidation(inputTime)) {
                            printUI.printCancel();
                            return Controller.MAIN_MENU_ACTION;
                        } else {
                            time = DateTime.getTime(inputTime, printUI);
                            if (time == null && inputTime.isEmpty()) {
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

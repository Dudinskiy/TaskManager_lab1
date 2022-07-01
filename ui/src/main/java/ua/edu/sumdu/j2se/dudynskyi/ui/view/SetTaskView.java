package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.ui.enums.Choice;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.DateTime;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.UserInput;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class SetTaskView implements View {

    private static final Logger logger = LoggerFactory.getLogger(SetTaskView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in the task list");
            System.out.println("===================");
            return Controller.MAIN_MENU_ACTION;
        }

        System.out.println("Enter the number of the task you want to edit from list below.");
        int number = 1;
        for (Task task : taskList) {
            if (task.isRepeated()) {
                System.out.println(number + ". " + task.getTitle() + " - repeat");
            } else {
                System.out.println(number + ". " + task.getTitle() + " - single");
            }
            number++;
        }

        int taskNumber;
        String choice;
        String title;
        LocalDateTime time;
        LocalDateTime startTime;
        LocalDateTime endTime;
        int repeatInterval;
        Task task;

        try {
            taskNumber = UserInput.inputTaskNumber(reader, taskList);
            task = taskList.getTask(taskNumber - 1);
            System.out.println(task + "\n");
            System.out.println("You can edit chosen task. If it is not necessary" +
                    " to change some data, just push enter.\n" +
                    "You can also change the type task from single to repeat and back.\n" +
                    "To change the repeat task just input a single time. To change the single task\n" +
                    "you need to input a start time, an end time and a repeat interval." +
                    "Do you want to change the type? Enter y or n:");

            while (true) {
                choice = reader.readLine();
                if (Validation.ynValidation(choice)) {
                    break;
                } else {
                    System.out.println("Incorrect value. Enter y or n");
                }
            }
            System.out.println("New title:");
            title = reader.readLine();
            if (!title.isEmpty()) {
                task.setTitle(title);
            }
            if (Choice.Y.toString().equalsIgnoreCase(choice)) {
                if (task.isRepeated()) {
                    System.out.println("new time(yyyy-mm-dd hh:mm):");
                    time = DateTime.inputTimeToSet(reader);
                    if (time == null) {
                        time = task.getEndTime();
                    }
                    task.setTime(time);

                } else {
                    System.out.println("new start time(yyyy-mm-dd hh:mm):");
                    startTime = DateTime.inputTimeToSet(reader);
                    System.out.println("new end time(yyyy-mm-dd hh:mm):");
                    endTime = DateTime.inputTimeToSet(reader);
                    System.out.println("new repeat interval:");
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
                    System.out.println("new start time(yyyy-mm-dd hh:mm):");
                    startTime = DateTime.inputTimeToSet(reader);
                    System.out.println("new end time(yyyy-mm-dd hh:mm):");
                    endTime = DateTime.inputTimeToSet(reader);

                    if (startTime != null && endTime != null) {
                        while (startTime.isAfter(endTime) || startTime.equals(endTime)) {
                            System.out.println("The end time must come after the start time. " +
                                    "Enter the end time correctly");
                            endTime = DateTime.inputTimeToCreate(reader);
                        }
                    }
                    System.out.println("new repeat interval:");
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
                } else {
                    System.out.println("new time(yyyy-mm-dd hh:mm):");
                    time = DateTime.getTimeFromString(reader.readLine());
                    if (time == null) {
                        time = task.getEndTime();
                    }
                    task.setTime(time);
                }
            }
            logger.info("The task {} has changed", task.getTitle());

        } catch (IOException e) {
            logger.error("User input error", e);
            System.out.println("Something wrong. Please try again");
        }
        System.out.println("===================");
        return Controller.MAIN_MENU_ACTION;
    }
}

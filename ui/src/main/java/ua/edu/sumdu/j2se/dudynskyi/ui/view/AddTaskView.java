package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.ui.enums.Choice;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.DateTime;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class AddTaskView implements View {

    private static final Logger logger = LoggerFactory.getLogger(AddTaskView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("You can create a single task or an repeat task.\n" +
                "Do you want to create a single task? Enter y or n:");
        String title;
        LocalDateTime time;
        LocalDateTime startTime;
        LocalDateTime endTime;
        int repeatInterval = 0;
        Task task;
        String choice;

        try {
            while (true) {
                choice = reader.readLine();
                if (Validation.ynValidation(choice)) {
                    break;
                } else {
                    System.out.println("Incorrect value. Enter y or n");
                }
            }
            System.out.println("title:");
            title = reader.readLine();
            if (Choice.Y.toString().equalsIgnoreCase(choice)) {
                System.out.println("time for single task (yyyy-mm-dd hh:mm):");
                time = DateTime.inputTimeToCreate(reader);
                task = new Task(title, time);
            } else {
                System.out.println("start time for repeat task (yyyy-mm-dd hh:mm):");
                startTime = DateTime.inputTimeToCreate(reader);
                System.out.println("end time for repeat task (yyyy-mm-dd hh:mm):");
                endTime = DateTime.inputTimeToCreate(reader);
                while (startTime.isAfter(endTime) || startTime.equals(endTime)) {
                    System.out.println("The end time must come after the start time. " +
                            "Enter the end time correctly");
                    endTime = DateTime.inputTimeToCreate(reader);
                }
                System.out.println("repeat interval:");
                String interval = reader.readLine();
                if (!interval.isEmpty()) {
                    repeatInterval = Integer.parseInt(interval);
                }
                task = new Task(title, startTime, endTime, repeatInterval);
            }
            task.setActive(true);
            taskList.add(task);
            System.out.println("New task added");
            logger.info("Task {} has added", task);

        } catch (IOException e) {
            logger.error("User input error", e);
            System.out.println("Something wrong. Please try again");
        }
        System.out.println("===================");

        return Controller.MAIN_MENU_ACTION;
    }
}

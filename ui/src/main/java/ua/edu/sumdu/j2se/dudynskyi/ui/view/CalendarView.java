package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Tasks;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.DateTime;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class CalendarView implements View {
    private static final Logger logger = LoggerFactory.getLogger(CalendarView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in the task list");
            System.out.println("===================");
            return Controller.MAIN_MENU_ACTION;
        }
        System.out.println("To get task calendar for a period, input a start date and an end date.");
        LocalDateTime start;
        LocalDateTime end;
        try {
            System.out.println("start time for repeat task (yyyy-mm-dd hh:mm):");
            start = DateTime.inputTimeToCreate(reader);
            System.out.println("end time for repeat task (yyyy-mm-dd hh:mm):");
            end = DateTime.inputTimeToCreate(reader);
            while(start.isAfter(end) || start.equals(end)){
                System.out.println("The end time must come after the start time. " +
                        "Enter the end time correctly");
                end = DateTime.inputTimeToCreate(reader);
            }

            SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList, start, end);
            System.out.println(calendar);

        } catch (IOException e) {
            logger.error("User input error", e);
            System.out.println("Something wrong. Please try again");
        }
        System.out.println("===================");

        return Controller.MAIN_MENU_ACTION;
    }
}

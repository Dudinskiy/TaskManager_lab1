package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Tasks;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.DateTime;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class CalendarView extends AbstractView {

    private static final Logger logger = LoggerFactory.getLogger(CalendarView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public CalendarView(UIPrintable printUI) {
        super(printUI);
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            printUI.printCancel();
            return Controller.MAIN_MENU_ACTION;
        }
        printUI.printGetCalendarHeadPhrase();

        LocalDateTime start;
        LocalDateTime end;
        String userInput;
        try {
            printUI.printInputStartTimeCalendar();
            while (true) {
                userInput = reader.readLine();
                if (Validation.cancelValidation(userInput)) {
                    printUI.printCancel();
                    return Controller.MAIN_MENU_ACTION;
                } else {
                    start = DateTime.getTime(userInput, printUI);
                    if (start != null) {
                        break;
                    }
                }
            }
            printUI.printInputEndTimeCalendar();
            while (true) {
                userInput = reader.readLine();
                if (Validation.cancelValidation(userInput)) {
                    printUI.printCancel();
                    return Controller.MAIN_MENU_ACTION;
                } else {
                    end = DateTime.getTime(userInput, printUI);
                    if (end != null) {
                        break;
                    }
                }
            }
            while (start.isAfter(end) || start.equals(end)) {
                printUI.printEndTimeBiggerStartTime();
                while (true) {
                    userInput = reader.readLine();
                    if (Validation.cancelValidation(userInput)) {
                        printUI.printCancel();
                        return Controller.MAIN_MENU_ACTION;
                    } else {
                        end = DateTime.getTime(userInput, printUI);
                        if (end != null) {
                            break;
                        }
                    }
                }
            }

            SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList, start, end);
            System.out.println(calendar);

        } catch (IOException e) {
            logger.error("User input error", e);
            printUI.printIsWrongTryAgain();
        }
        System.out.println("===================");

        return Controller.MAIN_MENU_ACTION;
    }
}

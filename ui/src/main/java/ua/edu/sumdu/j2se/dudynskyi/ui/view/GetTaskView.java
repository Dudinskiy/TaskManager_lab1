package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.UserInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetTaskView implements View{
    private static final Logger logger = LoggerFactory.getLogger(GetTaskView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if(taskList.size() == 0){
            System.out.println("There are no tasks in the task list");
            System.out.println("===================");
            return Controller.MAIN_MENU_ACTION;
        }
        System.out.println("To get a task, enter its order number from the list below.");
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
        try {
            taskNumber = UserInput.inputTaskNumber(reader, taskList);
            Task task = taskList.getTask(taskNumber - 1);
            System.out.println(task);
        } catch (IOException e) {
            logger.error("User input error", e);
            System.out.println("Something wrong. Please try again");
        }
        System.out.println("===================");
        return Controller.MAIN_MENU_ACTION;
    }
}

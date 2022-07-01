package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;

public class TaskListView implements View{
    @Override
    public int printInfo(AbstractTaskList taskList) {
        if(taskList.size() == 0){
            System.out.println("There are no tasks in the task list");
            System.out.println("===================");
            return Controller.MAIN_MENU_ACTION;
        }
        System.out.println("List of tasks:");
        int number = 1;
        for (Task task : taskList) {
            if (task.isRepeated()) {
                System.out.println(number + ". " + task.getTitle() + " - repeat");
            } else {
                System.out.println(number + ". " + task.getTitle() + " - single");
            }
            number++;
        }
        System.out.println("===================");
        return Controller.MAIN_MENU_ACTION;
    }
}

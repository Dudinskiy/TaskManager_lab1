package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;

public class TaskListView extends AbstractView {

    public TaskListView(UIPrintable printUI) {
        super(printUI);
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            printUI.printTaskListIsEmpty();
            return Controller.MAIN_MENU_ACTION;
        }
        printUI.printGetTaskListHeadPhrase();
        printUI.printTaskList(taskList);
        System.out.println("===================");
        return Controller.MAIN_MENU_ACTION;
    }
}

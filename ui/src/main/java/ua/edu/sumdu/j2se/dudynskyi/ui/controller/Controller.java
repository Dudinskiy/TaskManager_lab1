package ua.edu.sumdu.j2se.dudynskyi.ui.controller;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.ui.view.View;

public abstract class Controller {
    public static final int MAIN_MENU_ACTION = 0;
    public static final int TASK_LIST_ACTION = 1;
    public static final int ADD_TASK_ACTION = 2;
    public static final int CALENDAR_ACTION = 3;
    public static final int REMOVE_TASK_ACTION = 4;
    public static final int GET_TASK_ACTION = 5;
    public static final int SET_TASK_ACTION = 6;
    public static final int EXIT_ACTION = 7;

    protected View view;
    protected int actionToPerform = 0;

    public Controller(View view, int actionToPerform) {
        this.view = view;
        this.actionToPerform = actionToPerform;
    }

    public boolean canProcess(int action) {
        return action == actionToPerform;
    }

    public int process(AbstractTaskList taskList){
        return view.printInfo(taskList);
    }
}

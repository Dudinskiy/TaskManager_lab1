package ua.edu.sumdu.j2se.dudynskyi.ui.controller;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {

    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();
    private UIPrintable printUI;

    public MainController(View view, AbstractTaskList taskList, UIPrintable print) {
        super(view, Controller.MAIN_MENU_ACTION);
        this.taskList = taskList;
        this.printUI = print;

        controllers.add(this);
        controllers.add(new TaskListController(new TaskListView(printUI), Controller.TASK_LIST_ACTION));
        controllers.add(new AddTaskController(new AddTaskView(printUI), Controller.ADD_TASK_ACTION));
        controllers.add(new CalendarController(new CalendarView(printUI), Controller.CALENDAR_ACTION));
        controllers.add(new RemoveTaskController(new RemoveTaskView(printUI), Controller.REMOVE_TASK_ACTION));
        controllers.add(new GetTaskController(new GetTaskView(printUI), Controller.GET_TASK_ACTION));
        controllers.add(new SetTaskController(new SetTaskView(printUI), Controller.SET_TASK_ACTION));
        controllers.add(new SettingController(new SettingView(printUI), Controller.SETTING_ACTION));
    }

    public int process(AbstractTaskList taskList) {
        int action = view.printInfo(taskList);

        while (action != EXIT_ACTION) {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process(this.taskList);
                }
            }
        }
        return EXIT_ACTION;
    }
}

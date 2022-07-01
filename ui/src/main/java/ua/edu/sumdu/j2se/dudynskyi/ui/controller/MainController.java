package ua.edu.sumdu.j2se.dudynskyi.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.ui.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {

    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();

    public MainController(View view, AbstractTaskList taskList) {
        super(view, Controller.MAIN_MENU_ACTION);
        this.taskList = taskList;

        controllers.add(this);
        controllers.add(new TaskListController(new TaskListView(), Controller.TASK_LIST_ACTION));
        controllers.add(new AddTaskController(new AddTaskView(), Controller.ADD_TASK_ACTION));
        controllers.add(new CalendarController(new CalendarView(), Controller.CALENDAR_ACTION));
        controllers.add(new RemoveTaskController(new RemoveTaskView(), Controller.REMOVE_TASK_ACTION));
        controllers.add(new GetTaskController(new GetTaskView(), Controller.GET_TASK_ACTION));
        controllers.add(new SetTaskController(new SetTaskView(), Controller.SET_TASK_ACTION));
    }

    public int process(AbstractTaskList taskList) {
        int action = view.printInfo(taskList);
        for (; ; ) {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process(this.taskList);
                }
            }
            if (action == EXIT_ACTION) {
                break;
            }
        }
        return EXIT_ACTION;
    }
}

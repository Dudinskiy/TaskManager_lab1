package ua.edu.sumdu.j2se.dudynskyi.ui.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotificationManager implements Runnable {

    private AbstractTaskList taskList;
    private List<Notification> notifiers = new ArrayList<>();
    private static final int CHECK_INTERVAL = 60000;
    private static final Logger logger = LoggerFactory.getLogger(NotificationManager.class);

    public NotificationManager(AbstractTaskList taskList) {
        this.taskList = taskList;
        notifiers.add(new ScreenNotification());
    }

    public void checkTasks() {
        logger.info("Notification manager has started");
        for (; ; ) {
            Set<Task> taskSet = new HashSet<>();
            for (Task task : taskList) {
                taskSet.add(task);
            }
            for (Notification notifier : notifiers) {
                notifier.taskNotify(taskSet);
            }
            try {
                Thread.sleep(CHECK_INTERVAL);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        checkTasks();
    }
}

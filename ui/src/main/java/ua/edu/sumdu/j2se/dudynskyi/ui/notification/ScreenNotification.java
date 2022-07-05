package ua.edu.sumdu.j2se.dudynskyi.ui.notification;

import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ScreenNotification implements Notification {

    private UIPrintable printUI;

    public ScreenNotification(UIPrintable printUI) {
        this.printUI = printUI;
    }

    @Override
    public void taskNotify(Set<Task> taskSet) {
        LocalDateTime current = LocalDateTime.now().withSecond(0).withNano(0);
        for (Task task : taskSet) {
            if (task.isRepeated()) {
                Set<LocalDateTime> allDate = allDatesForRepeatTask(task);
                for (LocalDateTime time : allDate) {
                    if (current.isEqual(time)) {
                        printUI.printTaskNotification(task);
                    }
                }
            } else {
                if (current.isEqual(task.getTime())) {
                    printUI.printTaskNotification(task);
                }
            }
        }
    }

    private Set<LocalDateTime> allDatesForRepeatTask(Task task) {
        Set<LocalDateTime> result = new HashSet<>();
        LocalDateTime time = task.getStartTime();
        int interval = task.getRepeatInterval();

        while (time.isBefore(task.getEndTime()) || time.equals(task.getEndTime())) {
            result.add(time);
            time = time.plusSeconds(interval);
        }
        return result;
    }
}

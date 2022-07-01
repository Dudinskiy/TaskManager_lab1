package ua.edu.sumdu.j2se.dudynskyi.ui.notification;

import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ScreenNotification implements Notification {
    @Override
    public void taskNotify(Set<Task> taskSet) {
        LocalDateTime current = LocalDateTime.now().withSecond(0).withNano(0);
        for (Task task : taskSet) {
            if (task.isRepeated()) {
                Set<LocalDateTime> allDate = allDatesForRepeatTask(task);
                for (LocalDateTime time : allDate) {
                    if (current.isEqual(time)) {
                        System.out.println("===================");
                        System.out.println("You have to do the task: " + task.getTitle());
                        System.out.println("===================");
                    }
                }
            } else {
                if (current.isEqual(task.getTime())) {
                    System.out.println("===================");
                    System.out.println("You have to do the task: " + task.getTitle());
                    System.out.println("===================");
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

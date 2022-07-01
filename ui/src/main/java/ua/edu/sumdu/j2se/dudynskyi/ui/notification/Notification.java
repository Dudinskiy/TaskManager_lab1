package ua.edu.sumdu.j2se.dudynskyi.ui.notification;

import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;

import java.util.Set;

public interface Notification {
    void taskNotify(Set<Task> taskSet);
}

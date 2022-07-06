package ua.edu.sumdu.j2se.dudynskyi.tasks;

import java.util.Objects;

public class TaskListFactory {

    private TaskListFactory() {
    }

    public static AbstractTaskList createTaskList(ListTypes.types type) {
        Objects.requireNonNull(type, "ListTypes.types type must not be null!");

        if (ListTypes.types.ARRAY.equals(type)) {
            return new ArrayTaskList();
        } else if (ListTypes.types.LINKED.equals(type)) {
            return new LinkedTaskList();
        } else {
            throw new IllegalArgumentException(String.valueOf(type));
        }
    }
}

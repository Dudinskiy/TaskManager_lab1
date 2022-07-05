package ua.edu.sumdu.j2se.dudynskyi.tasks;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        LinkedTaskList tasks = new LinkedTaskList();
        Task task1 = new Task("r", LocalDateTime.now());

        tasks.add(task1);
        tasks.add(task1);
        tasks.add(task1);

        System.out.println(tasks);
    }
}

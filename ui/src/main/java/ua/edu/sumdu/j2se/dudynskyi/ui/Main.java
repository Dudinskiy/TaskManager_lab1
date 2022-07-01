package ua.edu.sumdu.j2se.dudynskyi.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.ArrayTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.TaskIO;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.MainController;
import ua.edu.sumdu.j2se.dudynskyi.ui.notification.NotificationManager;
import ua.edu.sumdu.j2se.dudynskyi.ui.view.MainView;
import ua.edu.sumdu.j2se.dudynskyi.ui.view.View;

import java.io.File;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Task manager has started\n");
        AbstractTaskList taskList = new ArrayTaskList();
        File file = new File("ui/src/main/resources/save.txt");
        if (file.length() != 0) {
            TaskIO.readBinary(taskList, file);
        }
        Runnable notificationManager = new NotificationManager(taskList);
        View mainView = new MainView();
        Controller mainController = new MainController(mainView, taskList);
        Thread thread = new Thread(notificationManager);
        thread.setDaemon(true);
        thread.start();
        logger.info("Application has started");
        mainController.process(null);
        TaskIO.writeBinary(taskList, file);
        System.out.println("Task manager has finished his work.\n" +
                "Our team grateful to you for using our product. Stay with us");
        logger.info("Application has finished");
    }
}

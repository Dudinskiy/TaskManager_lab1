package ua.edu.sumdu.j2se.dudynskyi.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.ArrayTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.TaskIO;
import ua.edu.sumdu.j2se.dudynskyi.tasks.exceptions.TaskIOException;
import ua.edu.sumdu.j2se.dudynskyi.ui.config.ConfigIO;
import ua.edu.sumdu.j2se.dudynskyi.ui.config.GeneralAppConfig;
import ua.edu.sumdu.j2se.dudynskyi.ui.config.TaskManagerConfig;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.MainController;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.notification.NotificationManager;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintableFactory;
import ua.edu.sumdu.j2se.dudynskyi.ui.view.MainView;
import ua.edu.sumdu.j2se.dudynskyi.ui.view.View;

import java.io.File;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private Main() {
    }

    public static void main(String[] args) {
        TaskManagerConfig config = new TaskManagerConfig();
        AbstractTaskList taskList = new ArrayTaskList();

        File saveTasksFile = new File(GeneralAppConfig.SAVE_TASKS_FILE_PATH);
        if (saveTasksFile.length() != 0) {
            try {
                TaskIO.readBinary(taskList, saveTasksFile);
            } catch (TaskIOException e) {
                logger.error("Exception when deserialization ArrayTaskList object");
            }
        }

        File saveConfigFile = new File(GeneralAppConfig.SAVE_CONFIG_FILE_PATH);
        if (saveConfigFile.length() != 0) {
            ConfigIO.readText(config, saveConfigFile);
        }
        UIPrintable printUI = UIPrintableFactory
                .createUIPrintable(config);

        Runnable notifyManager = new NotificationManager(taskList, printUI);
        View mainView = new MainView(printUI);
        Controller mainController = new MainController(mainView, taskList, printUI);
        Thread thread = new Thread(notifyManager);
        thread.setDaemon(true);
        thread.start();
        logger.info("Application has started");
        printUI.printStartPhrase();
        mainController.process(null);

        try {
            TaskIO.writeBinary(taskList, saveTasksFile);
        } catch (TaskIOException e) {
            logger.error("Exception when serialization ArrayTaskList object");
        }

        printUI.printFinishPhrase();
        logger.info("Application has finished");
    }
}

package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.ui.config.ConfigIO;
import ua.edu.sumdu.j2se.dudynskyi.ui.config.GeneralAppConfig;
import ua.edu.sumdu.j2se.dudynskyi.ui.config.TaskManagerConfig;
import ua.edu.sumdu.j2se.dudynskyi.ui.controller.Controller;
import ua.edu.sumdu.j2se.dudynskyi.ui.enums.Languages;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.Validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class SettingView extends AbstractView {

    private static final Logger logger = LoggerFactory.getLogger(AddTaskView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public SettingView(UIPrintable printUI) {
        super(printUI);
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        printUI.printSettingHeadPhrase();
        String userInput;
        Languages language;

        try {
            while (true) {
                userInput = reader.readLine();
                if (Validation.cancelValidation(userInput)) {
                    printUI.printCancel();
                    return Controller.MAIN_MENU_ACTION;
                }
                if (Validation.languageValidation(userInput)) {
                    break;
                } else {
                    printUI.printInvalidInputOfLanguage();
                }
            }
            language = Languages.valueOf(userInput.toUpperCase());

            TaskManagerConfig config = new TaskManagerConfig();

            File saveConfigFile = new File(GeneralAppConfig.SAVE_CONFIG_FILE_PATH);
            if (saveConfigFile.length() != 0) {
                ConfigIO.readText(config, saveConfigFile);
            }
            config.setLanguage(language);
            ConfigIO.writeText(config, saveConfigFile);

        } catch (IOException e) {
            logger.error("Exception when changing application settings", e);
        }
        System.out.println("===================");

        return Controller.MAIN_MENU_ACTION;
    }

}

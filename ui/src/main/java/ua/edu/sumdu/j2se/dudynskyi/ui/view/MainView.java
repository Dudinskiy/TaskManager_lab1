package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.UserInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements View {

    private static final Logger logger = LoggerFactory.getLogger(MainView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Choose your action:");
        System.out.println("1. Check your tasks");
        System.out.println("2. Add new task");
        System.out.println("3. Get task calendar");
        System.out.println("4. Remove task");
        System.out.println("5. Get task");
        System.out.println("6. Edit task");
        System.out.println("7. Exit");

        int actionNumber = 0;
        try {
            actionNumber = UserInput.inputActionNumber(reader);
        } catch (IOException e) {
            logger.error("User input error", e);
            System.out.println("Something wrong. Please try again");
        }
        return actionNumber;
    }
}

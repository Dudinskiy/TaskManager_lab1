package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.ui.exceptions.InputDataException;
import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;
import ua.edu.sumdu.j2se.dudynskyi.ui.utils.UserInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainView extends AbstractView {

    private static final Logger logger = LoggerFactory.getLogger(MainView.class);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public MainView(UIPrintable printUI) {
        super(printUI);
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        printUI.printMainMenu();
        int actionNumber = 0;
        try {
            actionNumber = UserInput.inputActionNumber(reader, printUI);
        } catch (InputDataException e) {
            logger.error("Exception in main menu when entering action number", e);
            printUI.printIsWrongTryAgain();
        }
        return actionNumber;
    }
}

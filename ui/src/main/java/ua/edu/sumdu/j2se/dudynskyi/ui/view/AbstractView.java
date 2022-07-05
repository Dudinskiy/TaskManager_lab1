package ua.edu.sumdu.j2se.dudynskyi.ui.view;

import ua.edu.sumdu.j2se.dudynskyi.ui.prints.UIPrintable;

public abstract class AbstractView implements View {

    protected UIPrintable printUI;

    public AbstractView(UIPrintable printUI) {
        this.printUI = printUI;
    }
}

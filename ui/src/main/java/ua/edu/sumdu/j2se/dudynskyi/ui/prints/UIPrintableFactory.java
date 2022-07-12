package ua.edu.sumdu.j2se.dudynskyi.ui.prints;

import ua.edu.sumdu.j2se.dudynskyi.ui.config.TaskManagerConfig;

public class UIPrintableFactory {

    private UIPrintableFactory() {
    }

    public static UIPrintable createUIPrintable(TaskManagerConfig config) {
        if (config != null && config.getLanguage() != null) {
            switch (config.getLanguage()) {
                case UKR:
                    return new PrintUkr();
                case RUS:
                    return new PrintRus();
                default:
                    return new PrintsEng();
            }
        } else {
            return new PrintsEng();
        }
    }
}

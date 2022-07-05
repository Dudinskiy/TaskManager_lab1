package ua.edu.sumdu.j2se.dudynskyi.ui.config;

import ua.edu.sumdu.j2se.dudynskyi.ui.enums.Languages;

import java.io.Serializable;
import java.util.Objects;

public class TaskManagerConfig implements Serializable {

    private static final long serialVersionUID = 21L;
    private Languages language = Languages.ENG;

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskManagerConfig that = (TaskManagerConfig) o;
        return language == that.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(language);
    }

    @Override
    public String toString() {
        return "TaskManagerConfig{" +
                "language=" + language +
                '}';
    }
}

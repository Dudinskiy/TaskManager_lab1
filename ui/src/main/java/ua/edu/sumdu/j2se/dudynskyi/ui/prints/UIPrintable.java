package ua.edu.sumdu.j2se.dudynskyi.ui.prints;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public interface UIPrintable {

    void printStartPhrase();
    void printFinishPhrase();
    void printGetTaskHeadPhrase();
    void printGetTaskListHeadPhrase();
    void printAddTaskHeadPhrase();
    void printRemoveTaskHeadPhase();
    void printSetTaskHeadPhrase();
    void printSelectTaskForSet();
    void printGetCalendarHeadPhrase();
    void printSettingHeadPhrase();
    void printTaskList(AbstractTaskList taskList);
    void printCancel();
    void printTaskListIsEmpty();
    void printMainMenu();
    void printEndTimeBiggerStartTime();
    void printInputStartTime();
    void printInputEndTime();
    void printInputTime();
    void printInputTaskTitle();
    void printInputRepeatInterval();
    void printInputStartTimeCalendar();
    void printInputEndTimeCalendar();
    void printTaskNotification(Task task);
    void printInvalidInputOfLanguage();
    void printInvalidYesNoInput();
    void printNewTaskAdded();
    void printIsWrongTryAgain();
    void printTaskRemoved();
    void printInputNewTime();
    void printInputNewStartTime();
    void printInputNewEndTime();
    void printInvalidActionNumber();
    void printInvalidNotNumeric();
    void printInvalidTaskNumber();
    void printInvalidPastTimeInput();
    void printInvalidDateInput();
    void printInvalidRepeatInterval();
    void printCalendar(SortedMap<LocalDateTime, Set<Task>> calendar);
    void printTask(Task task);
}

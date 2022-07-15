package ua.edu.sumdu.j2se.dudynskyi.ui.prints;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;

import java.time.LocalDateTime;
import java.util.*;

public class PrintsEng implements UIPrintable {

    public void printStartPhrase() {
        System.out.println("Task manager has started\n");
    }

    public void printFinishPhrase() {
        System.out.println("Task manager has finished his work.\n" +
                "Our team grateful to you for using our product.\n" +
                "Stay with us");
    }

    public void printGetTaskHeadPhrase() {
        System.out.println("To get a task, enter its order number from the list below.");
    }

    public void printGetTaskListHeadPhrase() {
        System.out.println("List of tasks:");
    }

    public void printAddTaskHeadPhrase() {
        System.out.println("You can create a single task or an repeat task.\n" +
                "If you want to cancel the creating of the task enter space.\n" +
                "Do you want to create a single task? Enter + or - :");
    }

    public void printRemoveTaskHeadPhase() {
        System.out.println("To delete a task, enter its order number from the list below");
    }

    public void printSetTaskHeadPhrase() {
        System.out.println("You can edit chosen task. If it is not necessary" +
                " to change some data, just push enter.\n" +
                "You can also change the type task from single to repeat and back.\n" +
                "To change the repeat task just input a single time. To change the single task\n" +
                "you need to input a start time, an end time and a repeat interval.\n" +
                "Do you want to change the type? Enter + or - :");
    }

    public void printSelectTaskForSet() {
        System.out.println("Enter the number of the task you want to edit from list below.");
    }

    public void printGetCalendarHeadPhrase() {
        System.out.println("To get task calendar for a period, input a start date and an end date.");
    }

    @Override
    public void printSettingHeadPhrase() {
        System.out.println("English, Ukraine and Russian languages available.\n" +
                "To select a language, enter eng, ukr or rus:");
    }

    public void printTaskList(AbstractTaskList taskList) {
        int number = 1;
        for (Task task : taskList) {
            if (task.isRepeated()) {
                System.out.println(number + ". " + task.getTitle() + " - repeat");
            } else {
                System.out.println(number + ". " + task.getTitle() + " - single");
            }
            number++;
        }
    }

    public void printCancel() {
        System.out.println("You canceled the action");
        System.out.println("===================");
    }

    public void printTaskListIsEmpty() {
        System.out.println("There are no tasks in the task list");
        System.out.println("===================");
    }

    public void printMainMenu() {
        System.out.println("Choose your action:");
        System.out.println("1. Check your tasks");
        System.out.println("2. Add new task");
        System.out.println("3. Get task calendar");
        System.out.println("4. Remove task");
        System.out.println("5. Get task");
        System.out.println("6. Edit task");
        System.out.println("7. Setting");
        System.out.println("8. Exit");
    }

    public void printEndTimeBiggerStartTime() {
        System.out.println("The end time must come after the start time. " +
                "Enter the end time correctly");
    }

    public void printInputStartTime() {
        System.out.println("start time for repeat task (yyyy-mm-dd hh:mm):");
    }

    public void printInputEndTime() {
        System.out.println("end time for repeat task (yyyy-mm-dd hh:mm):");
    }

    public void printInputTime() {
        System.out.println("time for single task (yyyy-mm-dd hh:mm):");
    }

    public void printInputTaskTitle() {
        System.out.println("title:");
    }

    public void printInputRepeatInterval() {
        System.out.println("repeat interval:");
    }

    public void printInputStartTimeCalendar() {
        System.out.println("start time (yyyy-mm-dd hh:mm):");
    }

    public void printInputEndTimeCalendar() {
        System.out.println("end time (yyyy-mm-dd hh:mm):");
    }

    public void printTaskNotification(Task task) {
        System.out.println("===================");
        System.out.println("You have to do the task: " + task.getTitle());
        System.out.println("===================");
    }

    @Override
    public void printInvalidInputOfLanguage() {
        System.out.println("Enter eng, ukr or rus");
    }

    @Override
    public void printInvalidYesNoInput() {
        System.out.println("Enter + or -");
    }

    @Override
    public void printNewTaskAdded() {
        System.out.println("New task added");
    }

    @Override
    public void printIsWrongTryAgain() {
        System.out.println("Something wrong. Please try again");
    }

    @Override
    public void printTaskRemoved() {
        System.out.println("The task removed");
    }

    @Override
    public void printInputNewTime() {
        System.out.println("new time(yyyy-mm-dd hh:mm):");
    }

    @Override
    public void printInputNewStartTime() {
        System.out.println("new start time(yyyy-mm-dd hh:mm):");
    }

    @Override
    public void printInputNewEndTime() {
        System.out.println("new end time(yyyy-mm-dd hh:mm):");
    }

    @Override
    public void printInvalidActionNumber() {
        System.out.println("There is no action with this number");
    }

    @Override
    public void printInvalidNotNumeric() {
        System.out.println("The value must be numeric");
    }

    @Override
    public void printInvalidTaskNumber() {
        System.out.println("There is no task with this number");
    }

    @Override
    public void printInvalidPastTimeInput() {
        System.out.println("You have entered a past date");
    }

    @Override
    public void printInvalidDateInput() {
        System.out.println("Invalid date value");
    }

    @Override
    public void printInvalidRepeatInterval() {
        System.out.println("Invalid interval value");
    }

    @Override
    public void printCalendar(SortedMap<LocalDateTime, Set<Task>> calendar) {
        if (calendar == null || calendar.isEmpty()) {
            System.out.println("No tasks for the specified period:");
            return;
        }

        int i = 1;
        System.out.println("Calendar of tasks for the specified period:");

        for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
            String date = entry.getKey().toString();
            List<Task> taskList = new ArrayList<>(entry.getValue());
            StringBuilder builder = new StringBuilder();
            for (Task value : taskList) {
                builder.append(value.getTitle()).append(",");
            }
            String str = builder.toString();
            String allTasks = str.substring(0, str.length() - 1);

            System.out.println(i + ") " + date.replace("T", " ") + " - " + allTasks);
            i++;
        }
    }

    @Override
    public void printTask(Task task) {
        if (task == null) {
            return;
        }
        if (task.isRepeated()) {
            int numberOfDays = task.getRepeatInterval() / 86400;
            int numberOfHours = (task.getRepeatInterval() % 86400) / 3600;
            int numberOfMinutes = ((task.getRepeatInterval() % 86400) % 3600) / 60;
            StringBuilder builder = new StringBuilder();
            String interval = "0";

            if (numberOfDays > 0) {
                builder.append(numberOfDays).append(" d. ")
                        .append(numberOfHours).append(" h. ")
                        .append(numberOfMinutes).append(" min.");
                interval = builder.toString();
            }
            if (numberOfDays == 0) {
                builder.append(numberOfHours).append(" h. ")
                        .append(numberOfMinutes).append(" min.");
                interval = builder.toString();
            }
            if (numberOfDays == 0 && numberOfHours == 0) {
                builder.append(numberOfMinutes).append(" min.");
                interval = builder.toString();
            }

            System.out.println("Task name - " + task.getTitle());
            System.out.println("Start time - "
                    + task.getStartTime().toString().replace("T", " "));
            System.out.println("End time - "
                    + task.getEndTime().toString().replace("T", " "));
            System.out.println("Interval - " + interval);
        } else {
            System.out.println("Task name - " + task.getTitle());
            System.out.println("Lead time - "
                    + task.getTime().toString().replace("T", " "));
        }
    }
}

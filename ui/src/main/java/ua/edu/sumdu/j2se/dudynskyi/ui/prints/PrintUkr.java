package ua.edu.sumdu.j2se.dudynskyi.ui.prints;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;

import java.time.LocalDateTime;
import java.util.*;

public class PrintUkr implements UIPrintable {
    @Override
    public void printStartPhrase() {
        System.out.println("Менеджер завдання розпочав роботу\n");
    }

    @Override
    public void printFinishPhrase() {
        System.out.println("Менеджер завершив роботу.\n" +
                "Наша команда вдячна Вам за вибір нашої продукції.\n" +
                "Залишайтеся з нами");
    }

    @Override
    public void printGetTaskHeadPhrase() {
        System.out.println("Щоб вибрати завдання, введіть номер зі списку нижче");
    }

    @Override
    public void printGetTaskListHeadPhrase() {
        System.out.println("Список завдань:");
    }

    @Override
    public void printAddTaskHeadPhrase() {
        System.out.println("Ви можете створити одиночну або повторювану задачу.\n" +
                "Якщо ви хочете скасувати операцію просто введіть пробіл.\n" +
                "Ви хочете створити поодиноке завдання? Введіть + або - :");
    }

    @Override
    public void printRemoveTaskHeadPhase() {
        System.out.println("Щоб видалити завдання, введіть номер зі списку нижче");
    }

    @Override
    public void printSetTaskHeadPhrase() {
        System.out.println("Ви можете редагувати вибране завдання. Якщо ви не бажаєте міняти" +
                " якісь дані просто натисніть enter.\n" +
                "Також ви можете змінити тип завдання з одиночної на повторювану і навпаки.\n" +
                "Щоб змінити тип завдання, що повторюється, просто введіть одиночний час. " +
                "Щоб змінити тип одиночної задачі\n" +
                "вам необхідно ввести час початку, час закінчення та інтервал повторення.\n" +
                "Ви бажаєте змінити тип завдання? Введіть + або - :");
    }

    @Override
    public void printSelectTaskForSet() {
        System.out.println("Введіть номер завдання, яке ви бажаєте редагувати, зі списку нижч");
    }

    @Override
    public void printGetCalendarHeadPhrase() {
        System.out.println("Щоб отримати календар завдань на період часу," +
                " введіть час початку та час закінчення.");
    }

    @Override
    public void printSettingHeadPhrase() {
        System.out.println("Доступні англійська, українська та російська мови.\n" +
                "Щоб вибрати мову, введіть eng, ukr або rus:");
    }

    @Override
    public void printTaskList(AbstractTaskList taskList) {
        int number = 1;
        for (Task task : taskList) {
            if (task.isRepeated()) {
                System.out.println(number + ". " + task.getTitle() + " - повторювана");
            } else {
                System.out.println(number + ". " + task.getTitle() + " - одиночна");
            }
            number++;
        }
    }

    @Override
    public void printCancel() {
        System.out.println("Ви скасували дію");
        System.out.println("===================");
    }

    @Override
    public void printTaskListIsEmpty() {
        System.out.println("У списку відсутні завдання");
        System.out.println("===================");
    }

    @Override
    public void printMainMenu() {
        System.out.println("Виберіть дію:");
        System.out.println("1. Перевірити завдання");
        System.out.println("2. Додати нове завдання");
        System.out.println("3. Отримати календар");
        System.out.println("4. Видалити завдання");
        System.out.println("5. Подивитись завдання");
        System.out.println("6. Редагувати завдання");
        System.out.println("7. Налаштування");
        System.out.println("8. Вихід");
    }

    @Override
    public void printEndTimeBiggerStartTime() {
        System.out.println("Час закінчення повинен йти після часу початку. " +
                "Введіть правильний час");
    }

    @Override
    public void printInputStartTime() {
        System.out.println("час початку виконання завдання (рррр-мм-дд гг:мм):");
    }

    @Override
    public void printInputEndTime() {
        System.out.println("час закінчення виконання завдання (рррр-мм-дд гг:мм):");
    }

    @Override
    public void printInputTime() {
        System.out.println("час виконання завдання (рррр-мм-дд гг:мм):");
    }

    @Override
    public void printInputTaskTitle() {
        System.out.println("назва:");
    }

    @Override
    public void printInputRepeatInterval() {
        System.out.println("інтервал повторення:");
    }

    @Override
    public void printInputStartTimeCalendar() {
        System.out.println("час початку (рррр-мм-дд гг:мм):");
    }

    @Override
    public void printInputEndTimeCalendar() {
        System.out.println("час закінчення (рррр-мм-дд гг:мм):");
    }

    @Override
    public void printTaskNotification(Task task) {
        System.out.println("===================");
        System.out.println("Вам необхідно виконати завдання: " + task.getTitle());
        System.out.println("===================");
    }

    @Override
    public void printInvalidInputOfLanguage() {
        System.out.println("Введіть eng, ukr або rus");
    }

    @Override
    public void printInvalidYesNoInput() {
        System.out.println("Введіть + або -");
    }

    @Override
    public void printNewTaskAdded() {
        System.out.println("Нове завдання додано");
    }

    @Override
    public void printIsWrongTryAgain() {
        System.out.println("Щось пішло не так. Спробуйте знову");
    }

    @Override
    public void printTaskRemoved() {
        System.out.println("Завдання видалено");
    }

    @Override
    public void printInputNewTime() {
        System.out.println("новий час (рррр-мм-дд гг:мм):");
    }

    @Override
    public void printInputNewStartTime() {
        System.out.println("новий час початку (рррр-мм-дд гг:мм):");
    }

    @Override
    public void printInputNewEndTime() {
        System.out.println("новий час закінчення (рррр-мм-дд гг:мм):");
    }

    @Override
    public void printInvalidActionNumber() {
        System.out.println("Немає дії з таким номером");
    }

    @Override
    public void printInvalidNotNumeric() {
        System.out.println("Значення має бути числовим");
    }

    @Override
    public void printInvalidTaskNumber() {
        System.out.println("Немає завдання з таким номером");
    }

    @Override
    public void printInvalidPastTimeInput() {
        System.out.println("Ви ввели минулу дату");
    }

    @Override
    public void printInvalidDateInput() {
        System.out.println("Неправильне значення дати");
    }

    @Override
    public void printInvalidRepeatInterval() {
        System.out.println("Неправильне значення інтервалу");
    }

    @Override
    public void printCalendar(SortedMap<LocalDateTime, Set<Task>> calendar) {
        if (calendar == null || calendar.isEmpty()) {
            System.out.println("Немає завдань на вказаний період:");
            return;
        }

        int i = 1;
        System.out.println("Календар завдань на вказаний період:");

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
                builder.append(numberOfDays).append(" дн. ")
                        .append(numberOfHours).append(" г. ")
                        .append(numberOfMinutes).append(" хв.");
                interval = builder.toString();
            }
            if (numberOfDays == 0) {
                builder.append(numberOfHours).append(" г. ")
                        .append(numberOfMinutes).append(" хв.");
                interval = builder.toString();
            }
            if (numberOfDays == 0 && numberOfHours == 0) {
                builder.append(numberOfMinutes).append(" хв.");
                interval = builder.toString();
            }

            System.out.println("Назва задачі - " + task.getTitle());
            System.out.println("Час початку - "
                    + task.getStartTime().toString().replace("T", " "));
            System.out.println("Час закінчення - "
                    + task.getEndTime().toString().replace("T", " "));
            System.out.println("Інтервал - " + interval);
        } else {
            System.out.println("Назва задачі - " + task.getTitle());
            System.out.println("Час виконання - "
                    + task.getTime().toString().replace("T", " "));
        }
    }
}

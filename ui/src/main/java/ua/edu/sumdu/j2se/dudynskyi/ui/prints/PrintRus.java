package ua.edu.sumdu.j2se.dudynskyi.ui.prints;

import ua.edu.sumdu.j2se.dudynskyi.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.dudynskyi.tasks.Task;

public class PrintRus implements UIPrintable {
    @Override
    public void printStartPhrase() {
        System.out.println("Менеджер задача начал работу\n");
    }

    @Override
    public void printFinishPhrase() {
        System.out.println("Менеджер завершил начал работу.\n" +
                "Наша команда благодарна Вам за выбор нашей продукции.\n" +
                "Оставайтесь с нами");
    }

    @Override
    public void printGetTaskHeadPhrase() {
        System.out.println("Чтобы выбрать задачу введите ее номер из списка ниже");
    }

    @Override
    public void printGetTaskListHeadPhrase() {
        System.out.println("Список задач:");
    }

    @Override
    public void printAddTaskHeadPhrase() {
        System.out.println("Вы можете создать одиночную либо повторяющуюся задачу.\n" +
                "Если вы хотите отменить операцию просто введите пробел.\n" +
                "Вы хотите создать одиночную задачу? Введите + или - :");
    }

    @Override
    public void printRemoveTaskHeadPhase() {
        System.out.println("Чтобы удалить задачу введите ее номер из списка ниже");
    }

    @Override
    public void printSetTaskHeadPhrase() {
        System.out.println("Вы можете отредактировать выбранную задачу. Если вы не желаете менять" +
                " какие-то данные, просто нажмите enter.\n" +
                "Также вы можете изменить тип задачи с одиночной на повторяющуюся и наоборот.\n" +
                "Чтобы изменить тип повторяющейся задачи просто введите одиночное время.\n" +
                "Чтобы изменить тип одиночной задачи вам необходимо ввести время начала,\n" +
                "время окончания и интервал повторения.\n" +
                "Вы хотите сменить тип задачи? Введите + или - :");
    }

    @Override
    public void printSelectTaskForSet() {
        System.out.println("Введите номер задачи, которую вы хотите редактировать, из списка ниже");
    }

    @Override
    public void printGetCalendarHeadPhrase() {
        System.out.println("Чтобы получить календарь задач на период времени," +
                " введите время начала и время окончания.");
    }

    @Override
    public void printSettingHeadPhrase() {
        System.out.println("Доступны английский, украинский и русский языки.\n" +
                "Чтобы выбрать язык введите eng, ukr или rus:");
    }

    @Override
    public void printTaskList(AbstractTaskList taskList) {
        int number = 1;
        for (Task task : taskList) {
            if (task.isRepeated()) {
                System.out.println(number + ". " + task.getTitle() + " - повторяемая");
            } else {
                System.out.println(number + ". " + task.getTitle() + " - одиночная");
            }
            number++;
        }
    }

    @Override
    public void printCancel() {
        System.out.println("Вы отменили действие");
        System.out.println("===================");
    }

    @Override
    public void printTaskListIsEmpty() {
        System.out.println("В списке отсутствуют задачи");
        System.out.println("===================");
    }

    @Override
    public void printMainMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Проверить задачи");
        System.out.println("2. Добавить новую задачу");
        System.out.println("3. Получить календарь");
        System.out.println("4. Удалить задачу");
        System.out.println("5. Посмотреть задачу");
        System.out.println("6. Редактировать задачу");
        System.out.println("7. Настройки");
        System.out.println("8. Выход");
    }

    @Override
    public void printEndTimeBiggerStartTime() {
        System.out.println("Время окончания должно следовать после времени начала. " +
                "Введите корректное время");
    }

    @Override
    public void printInputStartTime() {
        System.out.println("время начала выполения задачи (гггг-мм-дд чч:мм):");
    }

    @Override
    public void printInputEndTime() {
        System.out.println("время окончания выполения задачи (гггг-мм-дд чч:мм):");
    }

    @Override
    public void printInputTime() {
        System.out.println("время выполнения задачи (гггг-мм-дд чч:мм):");
    }

    @Override
    public void printInputTaskTitle() {
        System.out.println("название:");
    }

    @Override
    public void printInputRepeatInterval() {
        System.out.println("интервал повторения:");
    }

    @Override
    public void printInputStartTimeCalendar() {
        System.out.println("время начала (гггг-мм-дд чч:мм):");
    }

    @Override
    public void printInputEndTimeCalendar() {
        System.out.println("время окончания (гггг-мм-дд чч:мм):");
    }

    @Override
    public void printTaskNotification(Task task) {
        System.out.println("===================");
        System.out.println("Вам необходимо выполнить задачу: " + task.getTitle());
        System.out.println("===================");
    }

    @Override
    public void printInvalidInputOfLanguage() {
        System.out.println("Введите eng, ukr или rus");
    }

    @Override
    public void printInvalidYesNoInput() {
        System.out.println("Введите + или -");
    }

    @Override
    public void printNewTaskAdded() {
        System.out.println("Новая задача добавлена");
    }

    @Override
    public void printIsWrongTryAgain() {
        System.out.println("Что-то пошло не так. Попробуйте снова");
    }

    @Override
    public void printTaskRemoved() {
        System.out.println("Задача удалена");
    }

    @Override
    public void printInputNewTime() {
        System.out.println("новое время (гггг-мм-дд чч:мм):");
    }

    @Override
    public void printInputNewStartTime() {
        System.out.println("новое время начала (гггг-мм-дд чч:мм):");
    }

    @Override
    public void printInputNewEndTime() {
        System.out.println("новое время окончания (гггг-мм-дд чч:мм):");
    }

    @Override
    public void printInvalidActionNumber() {
        System.out.println("Нет дествия с таким номером");
    }

    @Override
    public void printInvalidNotNumeric() {
        System.out.println("Значение должно быть числовым");
    }

    @Override
    public void printInvalidTaskNumber() {
        System.out.println("Нет задачи с таким номером");
    }

    @Override
    public void printInvalidPastTimeInput() {
        System.out.println("Вы ввели прошедшую дату");
    }

    @Override
    public void printInvalidDateInput() {
        System.out.println("Неверное значение даты");
    }

    @Override
    public void printInvalidRepeatInterval() {
        System.out.println("Неверное значение интервала");
    }
}

package org.app.presenter;

import org.app.model.TaskDB;
import org.app.model.TaskModel;
import java.util.Date;
import java.util.List;

public class OrginizerPresenter {
    private final IOrginizerView view;
    private final TaskDB taskDB;

    public OrginizerPresenter(IOrginizerView view) {
        this.view = view;
        this.taskDB = new TaskDB();
    }

    public void loadTasks() {
        Date date = view.getSelectedDate();
        try {
            List<TaskModel> tasks = taskDB.getTasks(date);
            view.showTasks(tasks);
        } catch (Exception e) {
            view.showError("Ошибка загрузки задач");
        }
    }

    public void addTask(String text) {
        Date date = view.getSelectedDate();
        try {
            taskDB.addTask(text, date);
            loadTasks();
        } catch (Exception e) {
            view.showError("Ошибка добавления задачи");
        }
    }

    public void updateTask(TaskModel task) {
        try {
            taskDB.updateTask(task);
            loadTasks();
        } catch (Exception e) {
            view.showError("Ошибка изменения задачи");
        }
    }

    public void deleteTask(long id) {
        try {
            taskDB.deleteTask(id);
            loadTasks();
        } catch (Exception e) {
            view.showError("Ошибка удаления задачи");
        }
    }
}

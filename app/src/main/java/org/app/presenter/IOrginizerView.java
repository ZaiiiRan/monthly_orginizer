package org.app.presenter;

import org.app.model.TaskModel;

import java.util.Date;
import java.util.List;

public interface IOrginizerView {
    void showTasks(List<TaskModel> tasks);
    Date getSelectedDate();
    void showError(String message);
}

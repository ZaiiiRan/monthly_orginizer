package org.app.view;

import org.app.model.TaskModel;
import org.app.presenter.OrginizerPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonActionListener implements ActionListener {
    private final OrginizerPresenter presenter;
    private final JList<TaskModel> taskList;

    public DeleteButtonActionListener(OrginizerPresenter presenter, JList<TaskModel> taskList) {
        this.presenter = presenter;
        this.taskList = taskList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TaskModel task = taskList.getSelectedValue();
        if (task != null) {
            presenter.deleteTask(task.getId());
        }
    }
}

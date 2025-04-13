package org.app.view;

import org.app.model.TaskModel;
import org.app.presenter.OrginizerPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButtonActionListener implements ActionListener {
    private final OrginizerPresenter presenter;
    private final Component parentComponent;
    private final JList<TaskModel> taskList;

    public EditButtonActionListener(OrginizerPresenter presenter, Component parentComponent, JList<TaskModel> taskList) {
        this.presenter = presenter;
        this.parentComponent = parentComponent;
        this.taskList = taskList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TaskModel task = taskList.getSelectedValue();
        if (task != null) {
            String newText = JOptionPane.showInputDialog(parentComponent, "Изменить задачу:", task.getText());
            if (newText != null && !newText.trim().isEmpty()) {
                task.setText(newText.trim());
                presenter.updateTask(task);
            }
        } else {
            JOptionPane.showMessageDialog(parentComponent, "Выберите задачу для редактирования.", "Внимание", JOptionPane.WARNING_MESSAGE);
        }
    }
}

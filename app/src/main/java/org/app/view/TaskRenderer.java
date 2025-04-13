package org.app.view;

import org.app.model.TaskModel;

import javax.swing.*;
import java.awt.*;

public class TaskRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus
    ) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof TaskModel task) {
            String status = task.isDone() ? "[✔] " : "[ ] ";
            setText(status + task.getText());
        }
        return c;
    }
}

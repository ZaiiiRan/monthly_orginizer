package org.app.view;

import org.app.presenter.OrginizerPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonActionListener implements ActionListener {
    private final OrginizerPresenter presenter;
    private final Component parentComponent;

    public AddButtonActionListener(OrginizerPresenter presenter, Component parentComponent) {
        this.presenter = presenter;
        this.parentComponent = parentComponent;
    }
    @Override
    public  void actionPerformed(ActionEvent e) {
        String text = JOptionPane.showInputDialog(parentComponent, "Введите задачу:");
        if (text != null && !text.isEmpty()) {
            presenter.addTask(text);
        }
    }
}

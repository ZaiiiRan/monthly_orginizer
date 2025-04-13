package org.app;

import org.app.view.OrginizerView;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OrginizerView().setVisible(true);
            }
        });
    }
}

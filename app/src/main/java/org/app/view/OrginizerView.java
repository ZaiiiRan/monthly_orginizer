package org.app.view;

import org.app.model.TaskModel;
import org.app.presenter.IOrginizerView;
import org.app.presenter.OrginizerPresenter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class OrginizerView extends JFrame implements IOrginizerView {
    private final OrginizerPresenter presenter = new OrginizerPresenter(this);
    private final JPanel calendarPanel = new JPanel(new GridLayout(0, 7));
    private final JLabel monthLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel selectedDateLabel = new JLabel();
    private final DefaultListModel<TaskModel> taskListModel = new DefaultListModel<>();
    private final JList<TaskModel> taskList = new JList<>(taskListModel);
    private final JButton prevButton = new JButton("<");
    private final JButton nextButton = new JButton(">");
    private final Calendar currentCalendar = Calendar.getInstance();
    private Date selectedDate = new Date();

    public OrginizerView() {
        setTitle("Органайзер на месяц");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI();
        updateCalendar();
    }

    private void initUI() {
        JPanel topPanel = new JPanel(new BorderLayout());
        monthLabel.setFont(new Font("Arial", Font.BOLD, 20));
        selectedDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel monthPanel = new JPanel(new BorderLayout());
        monthPanel.add(prevButton, BorderLayout.WEST);
        monthPanel.add(monthLabel, BorderLayout.CENTER);
        monthPanel.add(nextButton, BorderLayout.EAST);

        topPanel.add(monthPanel, BorderLayout.NORTH);
        topPanel.add(selectedDateLabel, BorderLayout.SOUTH);

        prevButton.addActionListener(e -> {
            currentCalendar.add(Calendar.MONTH, -1);
            updateCalendar();
        });

        nextButton.addActionListener(e -> {
            currentCalendar.add(Calendar.MONTH, 1);
            updateCalendar();
        });

        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        taskList.setCellRenderer(new TaskRenderer());

        JScrollPane scrollPane = new JScrollPane(taskList);

        JPanel controls = new JPanel();
        JButton addBtn = new JButton("Добавить");
        JButton editBtn = new JButton("Редактировать");
        JButton deleteBtn = new JButton("Удалить");
        JButton markDoneBtn = new JButton("✓ / ✗");

        controls.add(addBtn);
        controls.add(editBtn);
        controls.add(deleteBtn);
        controls.add(markDoneBtn);

        addBtn.addActionListener(new AddButtonActionListener(presenter, this));
        editBtn.addActionListener(new EditButtonActionListener(presenter, this, taskList));
        deleteBtn.addActionListener(new DeleteButtonActionListener(presenter, taskList));
        markDoneBtn.addActionListener(new MarkDoneButtonActionListener(presenter, taskList));

        taskPanel.add(scrollPane, BorderLayout.CENTER);
        taskPanel.add(controls, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);
        add(taskPanel, BorderLayout.SOUTH);

        presenter.loadTasks();
    }

    private void updateCalendar() {
        calendarPanel.removeAll();

        String[] months = {
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };

        int month = currentCalendar.get(Calendar.MONTH);
        int year = currentCalendar.get(Calendar.YEAR);
        monthLabel.setText(months[month] + " " + year);

        Calendar temp = (Calendar) currentCalendar.clone();
        temp.set(Calendar.DAY_OF_MONTH, 1);
        int firstDay = temp.get(Calendar.DAY_OF_WEEK) - 1;
        if (firstDay == 0) firstDay = 7;

        temp.add(Calendar.DAY_OF_MONTH, -firstDay + 1);

        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));
        selectedDateLabel.setText("Выбрано: " + df.format(selectedDate));

        for (int i = 0; i < 42; i++) {
            JButton dayBtn = new JButton(String.valueOf(temp.get(Calendar.DAY_OF_MONTH)));
            Date dateForBtn = temp.getTime();

            if (temp.get(Calendar.MONTH) != currentCalendar.get(Calendar.MONTH)) {
                dayBtn.setEnabled(false);
            }

            dayBtn.addActionListener(e -> {
                selectedDate = dateForBtn;
                selectedDateLabel.setText("Выбрано: " + df.format(selectedDate));
                presenter.loadTasks();
            });

            calendarPanel.add(dayBtn);
            temp.add(Calendar.DAY_OF_MONTH, 1);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    @Override
    public void showTasks(List<TaskModel> tasks) {
        taskListModel.clear();
        for (TaskModel task : tasks) {
            taskListModel.addElement(task);
        }
    }

    @Override
    public Date getSelectedDate() {
        return selectedDate;
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}

package org.app.model;

import org.app.db.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDB {
    public List<TaskModel> getTasks(Date date) throws Exception {
        List<TaskModel> tasks = new ArrayList<>();
        try (PreparedStatement ps = DatabaseManager.getInstance().getConnection()
                .prepareStatement("SELECT * FROM tasks WHERE date = ?")) {
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tasks.add(new TaskModel(
                        rs.getLong("id"),
                        rs.getDate("date"),
                        rs.getString("text"),
                        rs.getBoolean("done")
                ));
            }

            return tasks;
        } catch (SQLException e) {
            throw new Exception();
        }
    }

    public void addTask(String text, Date date) throws Exception {
        try (PreparedStatement ps = DatabaseManager.getInstance().getConnection()
                .prepareStatement("INSERT INTO tasks(date, text) VALUES (?, ?)")) {
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ps.setString(2, text);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception();
        }
    }

    public void updateTask(TaskModel task) throws Exception {
        try (PreparedStatement ps = DatabaseManager.getInstance().getConnection()
                .prepareStatement("UPDATE tasks SET text = ?, done = ? WHERE id = ?")) {
            ps.setString(1, task.getText());
            ps.setBoolean(2, task.isDone());
            ps.setLong(3, task.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception();
        }
    }

    public void deleteTask(long id) throws Exception {
        try (PreparedStatement ps = DatabaseManager.getInstance().getConnection()
                .prepareStatement("DELETE FROM tasks WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception();
        }
    }
}

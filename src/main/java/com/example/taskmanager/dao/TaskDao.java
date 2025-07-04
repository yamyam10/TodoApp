package com.example.taskmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.taskmanager.bean.Task;

public class TaskDao extends DAO {

    // タスク一覧取得（フィルタ・ソート対応）
    public List<Task> getTasks(String filter, String sort) {
        List<Task> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM tasks");

        if ("done".equals(filter)) {
            sql.append(" WHERE done = 1");
        } else if ("undone".equals(filter)) {
            sql.append(" WHERE done = 0");
        }

        sql.append(" ORDER BY deadline ");
        sql.append("desc".equals(sort) ? "DESC" : "ASC");

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getString("id"));
                task.setTitle(rs.getString("title"));
                task.setDeadline(rs.getString("deadline"));
                task.setDone(rs.getString("done"));
                task.setCreatedAt(rs.getString("created_at"));
                list.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // タスクの追加
    public void insertTask(String title, String deadline) {
        String sql = "INSERT INTO tasks (id, title, deadline, done, created_at) VALUES (task_seq.NEXTVAL, ?, TO_DATE(?, 'YYYY-MM-DD'), 0, SYSDATE)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, deadline);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // タスクの完了状態更新
    public void updateTaskDone(String id, String done) {
        String sql = "UPDATE tasks SET done = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, "1".equals(done) ? 1 : 0);
            ps.setInt(2, Integer.parseInt(id));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package service;

import util.JDBCUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskManagement {

    public void addTask(String taskName, String status) {
        String sql = "{CALL add_task(?, ?)}";
        try (Connection conn = JDBCUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, taskName);
            cs.setString(2, status);
            cs.executeUpdate();
            System.out.println("✅ Đã thêm công việc: " + taskName);

        } catch (SQLException e) {
            System.err.println("❌ Lỗi thêm công việc: " + e.getMessage());
        }
    }

    public void listTasks() {
        String sql = "{CALL list_tasks()}";
        try (Connection conn = JDBCUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            System.out.println("📋 Danh sách công việc:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + " | Tên: " + rs.getString("task_name")
                        + " | Trạng thái: " + rs.getString("status"));
            }

        } catch (SQLException e) {
            System.err.println("❌ Lỗi liệt kê công việc: " + e.getMessage());
        }
    }

    public void updateTaskStatus(int taskId, String status) {
        String sql = "{CALL update_task_status(?, ?)}";
        try (Connection conn = JDBCUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, taskId);
            cs.setString(2, status);
            cs.executeUpdate();
            System.out.println("✅ Đã cập nhật trạng thái công việc ID: " + taskId);

        } catch (SQLException e) {
            System.err.println("❌ Lỗi cập nhật trạng thái: " + e.getMessage());
        }
    }

    public void deleteTask(int taskId) {
        String sql = "{CALL delete_task(?)}";
        try (Connection conn = JDBCUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, taskId);
            cs.executeUpdate();
            System.out.println("✅ Đã xoá công việc ID: " + taskId);

        } catch (SQLException e) {
            System.err.println("❌ Lỗi xoá công việc: " + e.getMessage());
        }
    }

    public void searchTaskByName(String taskName) {
        String sql = "{CALL search_task_by_name(?)}";
        try (Connection conn = JDBCUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, taskName);
            try (ResultSet rs = cs.executeQuery()) {
                System.out.println("🔍 Kết quả tìm kiếm:");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id")
                            + " | Tên: " + rs.getString("task_name")
                            + " | Trạng thái: " + rs.getString("status"));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Lỗi tìm kiếm công việc: " + e.getMessage());
        }
    }

    public void taskStatistics() {
        String sql = "{CALL task_statistics()}";
        try (Connection conn = JDBCUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                int completed = rs.getInt("completed");
                int pending = rs.getInt("pending");
                System.out.println("📊 Thống kê:");
                System.out.println("✅ Đã hoàn thành: " + completed);
                System.out.println("⏳ Chưa hoàn thành: " + pending);
            }

        } catch (SQLException e) {
            System.err.println("❌ Lỗi thống kê công việc: " + e.getMessage());
        }
    }
}
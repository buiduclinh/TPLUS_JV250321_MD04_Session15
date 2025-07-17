package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

    public static <T> T executeQuery(String sql, ResultSetHandler<T> handler, Object... params) {
        try (Connection conn = JDBCUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            // Gán tham số nếu có
            for (int i = 0; i < params.length; i++) {
                cs.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = cs.executeQuery()) {
                return handler.handle(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean executeUpdate(String sql, Object... params) {
        try (Connection conn = JDBCUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            // Gán tham số động
            for (int i = 0; i < params.length; i++) {
                cs.setObject(i + 1, params[i]);
            }

            return cs.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi executeUpdate: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
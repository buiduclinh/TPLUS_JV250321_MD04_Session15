package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static JDBCUtil instance;
    private static final String URL = "jdbc:mysql://localhost:3306/Cinema";
    private static final String USER = "root";
    private static final String PASS = "123456789";

    private JDBCUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Đảm bảo JDBC Driver nạp
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static JDBCUtil getInstance() {
        if (instance == null) {
            instance = new JDBCUtil();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
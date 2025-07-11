import java.sql.*;

public class MovieManagement {
    private final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    private final String USER = "root";
    private final String PASS = "root";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Thêm phim
    public void addMovie(String title, String director, int year) {
        try (Connection conn = connect()) {
            CallableStatement cs = conn.prepareCall("{CALL add_movie(?, ?, ?)}");
            cs.setString(1, title);
            cs.setString(2, director);
            cs.setInt(3, year);
            cs.execute();
            System.out.println("✅ Đã thêm phim thành công!");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi thêm phim: " + e.getMessage());
        }
    }

    // Liệt kê phim
    public void listMovies() {
        try (Connection conn = connect()) {
            CallableStatement cs = conn.prepareCall("{CALL list_movies()}");
            ResultSet rs = cs.executeQuery();
            System.out.println("🎬 Danh sách phim:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". "
                        + rs.getString("title") + " | "
                        + rs.getString("director") + " | "
                        + rs.getInt("release_year"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi liệt kê phim: " + e.getMessage());
        }
    }

    // Sửa phim
    public void updateMovie(int id, String title, String director, int year) {
        try (Connection conn = connect()) {
            CallableStatement cs = conn.prepareCall("{CALL update_movie(?, ?, ?, ?)}");
            cs.setInt(1, id);
            cs.setString(2, title);
            cs.setString(3, director);
            cs.setInt(4, year);
            cs.execute();
            System.out.println("✅ Đã cập nhật phim thành công!");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi cập nhật phim: " + e.getMessage());
        }
    }

    // Xóa phim
    public void deleteMovie(int id) {
        try (Connection conn = connect()) {
            CallableStatement cs = conn.prepareCall("{CALL delete_movie(?)}");
            cs.setInt(1, id);
            cs.execute();
            System.out.println("✅ Đã xóa phim thành công!");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi xóa phim: " + e.getMessage());
        }
    }
}

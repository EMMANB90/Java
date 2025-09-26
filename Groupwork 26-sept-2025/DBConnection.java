package attendancetrackingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3308/attendance_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";  
    private static final String PASSWORD = ""; 

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
            throw e;
        }
    }
    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("✅ Connected to database!");
        } catch (SQLException e) {
            System.out.println("❌ Connection test failed.");
        }
    }

}

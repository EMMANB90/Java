/* Byiringiro Emmanuel 223013645
 *Uwimana Angelique 223010289
 *Murungi Peace 223019597
 * */



package attendancetrackingsystem;


import java.sql.*;
import java.util.*;

public class AttendanceDAO {

    // Insert
    public static void addRecord(String studentId, String name, String status) throws Exception {
        String sql = "INSERT INTO attendance (student_id, name, status) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ps.setString(2, name);
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public static boolean updateRecord(String studentId, String newName, String newStatus) throws Exception {
        String sql = "UPDATE attendance SET name = ?, status = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setString(2, newStatus);
            ps.setString(3, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public static boolean deleteRecord(String studentId) throws Exception {
        String sql = "DELETE FROM attendance WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all records
    public static List<String[]> getAllRecords() throws Exception {
        List<String[]> records = new ArrayList<>();
        String sql = "SELECT student_id, name, status FROM attendance ORDER BY id DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add(new String[]{
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getString("status")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}

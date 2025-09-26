package attendancetrackingsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AttendanceSystemGUI extends JFrame {
    private DefaultTableModel tableModel;

    // Input fields
    private JTextField txtIdAdd, txtNameAdd;
    private JComboBox<String> cbStatusAdd;

    private JTextField txtIdUpdate, txtNameUpdate;
    private JComboBox<String> cbStatusUpdate;

    private JTextField txtIdDelete;

    public AttendanceSystemGUI() {
        setTitle("Attendance Tracking System (DB Version)");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // --- Add Record Tab ---
        JPanel addPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        txtIdAdd = new JTextField();
        txtNameAdd = new JTextField();
        cbStatusAdd = new JComboBox<>(new String[]{"Present", "Absent"});
        JButton btnAdd = new JButton("Add Record");
        btnAdd.addActionListener(e -> addRecord());

        addPanel.add(new JLabel("Student ID:"));
        addPanel.add(txtIdAdd);
        addPanel.add(new JLabel("Name:"));
        addPanel.add(txtNameAdd);
        addPanel.add(new JLabel("Status:"));
        addPanel.add(cbStatusAdd);
        addPanel.add(new JLabel());
        addPanel.add(btnAdd);

        tabs.addTab("Add Record", addPanel);

        // --- Update Tab ---
        JPanel updatePanel = new JPanel(new GridLayout(4, 2, 10, 10));
        txtIdUpdate = new JTextField();
        txtNameUpdate = new JTextField();
        cbStatusUpdate = new JComboBox<>(new String[]{"Present", "Absent"});
        JButton btnUpdate = new JButton("Update Record");
        btnUpdate.addActionListener(e -> updateRecord());

        updatePanel.add(new JLabel("Student ID to Update:"));
        updatePanel.add(txtIdUpdate);
        updatePanel.add(new JLabel("New Name:"));
        updatePanel.add(txtNameUpdate);
        updatePanel.add(new JLabel("New Status:"));
        updatePanel.add(cbStatusUpdate);
        updatePanel.add(new JLabel());
        updatePanel.add(btnUpdate);

        tabs.addTab("Update Record", updatePanel);

        // --- Delete Tab ---
        JPanel deletePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        txtIdDelete = new JTextField();
        JButton btnDelete = new JButton("Delete Record");
        btnDelete.addActionListener(e -> deleteRecord());

        deletePanel.add(new JLabel("Student ID to Delete:"));
        deletePanel.add(txtIdDelete);
        deletePanel.add(new JLabel());
        deletePanel.add(btnDelete);

        tabs.addTab("Delete Record", deletePanel);

        // --- View Tab ---
        JPanel viewPanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"Student ID", "Name", "Status"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton btnLoad = new JButton("Load Records");
        btnLoad.addActionListener(e -> loadRecords());

        viewPanel.add(scrollPane, BorderLayout.CENTER);
        viewPanel.add(btnLoad, BorderLayout.SOUTH);

        tabs.addTab("View Records", viewPanel);

        add(tabs);
        loadRecords();
    }

    // --- Add Record ---
    private void addRecord() {
        String id = txtIdAdd.getText().trim();
        String name = txtNameAdd.getText().trim();
        String status = (String) cbStatusAdd.getSelectedItem();

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        String sql = "INSERT INTO attendance(student_id, name, status) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, status);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record Added!");
            loadRecords();
            txtIdAdd.setText("");
            txtNameAdd.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding record: " + e.getMessage());
        }
    }

    // --- Update Record ---
    private void updateRecord() {
        String id = txtIdUpdate.getText().trim();
        String newName = txtNameUpdate.getText().trim();
        String newStatus = (String) cbStatusUpdate.getSelectedItem();

        String sql = "UPDATE attendance SET name=?, status=? WHERE student_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, newName);
            pst.setString(2, newStatus);
            pst.setString(3, id);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record Updated!");
                loadRecords();
            } else {
                JOptionPane.showMessageDialog(this, "Record Not Found!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating record: " + e.getMessage());
        }
    }

    // --- Delete Record ---
    private void deleteRecord() {
        String id = txtIdDelete.getText().trim();

        String sql = "DELETE FROM attendance WHERE student_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, id);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record Deleted!");
                loadRecords();
            } else {
                JOptionPane.showMessageDialog(this, "Record Not Found!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting record: " + e.getMessage());
        }
    }

    // --- Load Records ---
    private void loadRecords() {
        tableModel.setRowCount(0); // clear table first

        String sql = "SELECT student_id, name, status FROM attendance";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getString("status")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading records: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceSystemGUI().setVisible(true));
    }
}

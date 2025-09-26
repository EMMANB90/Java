

package com.panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

import com.util.DB;

public class CoursePanel extends JPanel implements ActionListener {

    JTextField idTxt = new JTextField(), nameTxt = new JTextField(), codeTxt = new JTextField();
    JComboBox<String> teacherCmb = new JComboBox<>();
    JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"),
            deleteBtn = new JButton("Delete"), loadBtn = new JButton("Load");
    JTable table;
    DefaultTableModel model;

    public CoursePanel() {
        setLayout(null);

        String[] columns = {"ID", "Course Name", "Course Code", "Teacher"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 230, 800, 300);
        add(sp);

        int y = 20;
        addField("ID", idTxt, y); y += 30;
        addField("Course Name", nameTxt, y); y += 30;
        addField("Course Code", codeTxt, y); y += 30;
        addComboField("Teacher", teacherCmb, y);

        idTxt.setEditable(false);

        addButtons();
        loadTeachers(); // optional: fill teacher combo
        loadCourses();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    idTxt.setText(model.getValueAt(row, 0).toString());
                    nameTxt.setText(model.getValueAt(row, 1).toString());
                    codeTxt.setText(model.getValueAt(row, 2).toString());
                    teacherCmb.setSelectedItem(model.getValueAt(row, 3).toString());
                }
            }
        });
    }

    private void addField(String lbl, JTextField field, int y) {
        JLabel l = new JLabel(lbl);
        l.setBounds(20, y, 100, 25);
        field.setBounds(130, y, 150, 25);
        add(l); add(field);
    }

    private void addComboField(String lbl, JComboBox<String> combo, int y) {
        JLabel l = new JLabel(lbl);
        l.setBounds(20, y, 100, 25);
        combo.setBounds(130, y, 150, 25);
        add(l); add(combo);
    }

    private void addButtons() {
        addBtn.setBounds(300, 20, 100, 30);
        updateBtn.setBounds(300, 60, 100, 30);
        deleteBtn.setBounds(300, 100, 100, 30);
        loadBtn.setBounds(300, 140, 100, 30);
        add(addBtn); add(updateBtn); add(deleteBtn); add(loadBtn);
        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        loadBtn.addActionListener(this);
    }

    private void loadTeachers() {
        try (Connection con = DB.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT name FROM teacher");
            teacherCmb.removeAllItems();
            while (rs.next()) {
                teacherCmb.addItem(rs.getString("name"));
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void actionPerformed(ActionEvent e) {
        try (Connection con = DB.getConnection()) {
            if (e.getSource() == addBtn) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO course(course_name, course_code, teacher) VALUES(?,?,?)");
                ps.setString(1, nameTxt.getText());
                ps.setString(2, codeTxt.getText());
                ps.setString(3, teacherCmb.getSelectedItem().toString());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Course Added!");
                loadCourses();
            } else if (e.getSource() == updateBtn) {
                if (idTxt.getText().isEmpty()) { JOptionPane.showMessageDialog(this, "Select a course to update!"); return; }
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE course SET course_name=?, course_code=?, teacher=? WHERE course_id=?");
                ps.setString(1, nameTxt.getText());
                ps.setString(2, codeTxt.getText());
                ps.setString(3, teacherCmb.getSelectedItem().toString());
                ps.setInt(4, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Course Updated!");
                loadCourses();
            } else if (e.getSource() == deleteBtn) {
                if (idTxt.getText().isEmpty()) { JOptionPane.showMessageDialog(this, "Select a course to delete!"); return; }
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    PreparedStatement ps = con.prepareStatement("DELETE FROM course WHERE course_id=?");
                    ps.setInt(1, Integer.parseInt(idTxt.getText()));
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Course Deleted!");
                    loadCourses();
                }
            } else if (e.getSource() == loadBtn) {
                loadCourses();
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void loadCourses() {
        try (Connection con = DB.getConnection()) {
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM course");
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("course_code"),
                        rs.getString("teacher")
                });
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}

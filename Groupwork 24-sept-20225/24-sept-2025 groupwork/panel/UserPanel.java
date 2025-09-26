package com.panel;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.util.DB;

public class UserPanel extends JPanel implements ActionListener {

    JTextField idTxt = new JTextField(),
               nameTxt = new JTextField(),
               phoneTxt = new JTextField(),
               emailTxt = new JTextField();
    JPasswordField passTxt = new JPasswordField();
    JComboBox<String> roleCmb = new JComboBox<>(new String[]{"admin", "teacher", "student"});

    JButton addBtn = new JButton("Add"), 
            updateBtn = new JButton("Update"),
            deleteBtn = new JButton("Delete"), 
            loadBtn = new JButton("Load");

    JTable table; 
    DefaultTableModel model;

    public UserPanel() {
        setLayout(null);

        // Table
        String[] columns = {"ID", "Username", "Password", "Phone", "Email", "Role"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 230, 800, 300);
        add(sp);

        // Fields
        int y = 20;
        addField("ID", idTxt, y); y += 30;
        addField("Username", nameTxt, y); y += 30;
        addField("Password", passTxt, y); y += 30;
        addField("Phone", phoneTxt, y); y += 30;
        addField("Email", emailTxt, y); y += 30;
        addComboField("Role", roleCmb, y);

        idTxt.setEditable(false); // Auto increment

        // Buttons
        addButtons();

        // Load users on startup
        loadUsers();

        // Fill fields when row clicked
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    idTxt.setText(model.getValueAt(row, 0).toString());
                    nameTxt.setText(model.getValueAt(row, 1).toString());
                    passTxt.setText(model.getValueAt(row, 2).toString());
                    phoneTxt.setText(model.getValueAt(row, 3).toString());
                    emailTxt.setText(model.getValueAt(row, 4).toString());
                    roleCmb.setSelectedItem(model.getValueAt(row, 5).toString());
                }
            }
        });
    }

    private void addField(String label, JComponent field, int y) {
        JLabel l = new JLabel(label);
        l.setBounds(20, y, 80, 25);
        field.setBounds(100, y, 150, 25);
        add(l);
        add(field);
    }

    private void addComboField(String label, JComboBox<String> combo, int y) {
        JLabel l = new JLabel(label);
        l.setBounds(20, y, 80, 25);
        combo.setBounds(100, y, 150, 25);
        add(l);
        add(combo);
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

    public void actionPerformed(ActionEvent e) {
        try (Connection con = DB.getConnection()) {

            if (e.getSource() == addBtn) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO user(username,password,phone,email,role) VALUES(?,?,?,?,?)");
                ps.setString(1, nameTxt.getText());
                ps.setString(2, new String(passTxt.getPassword()));
                ps.setString(3, phoneTxt.getText());
                ps.setString(4, emailTxt.getText());
                ps.setString(5, roleCmb.getSelectedItem().toString());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User Added!");
                loadUsers();

            } else if (e.getSource() == updateBtn) {
                if (idTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a user to update!");
                    return;
                }
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE user SET username=?, password=?, phone=?, email=?, role=? WHERE userid=?");
                ps.setString(1, nameTxt.getText());
                ps.setString(2, new String(passTxt.getPassword()));
                ps.setString(3, phoneTxt.getText());
                ps.setString(4, emailTxt.getText());
                ps.setString(5, roleCmb.getSelectedItem().toString());
                ps.setInt(6, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User Updated!");
                loadUsers();

            } else if (e.getSource() == deleteBtn) {
                if (idTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a user to delete!");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete this user?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    PreparedStatement ps = con.prepareStatement("DELETE FROM user WHERE userid=?");
                    ps.setInt(1, Integer.parseInt(idTxt.getText()));
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "User Deleted!");
                    loadUsers();
                }

            } else if (e.getSource() == loadBtn) {
                loadUsers();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadUsers() {
        try (Connection con = DB.getConnection()) {
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM user");
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("userid"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("role")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

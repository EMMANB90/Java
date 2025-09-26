

package com.panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

import com.util.DB;

public class TeacherPanel extends JPanel implements ActionListener {

    JTextField idTxt = new JTextField(), nameTxt = new JTextField(), phoneTxt = new JTextField(), emailTxt = new JTextField();
    JPasswordField passTxt = new JPasswordField();
    JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"),
            deleteBtn = new JButton("Delete"), loadBtn = new JButton("Load");
    JTable table; DefaultTableModel model;

    public TeacherPanel() {
        setLayout(null);
        String[] columns = {"ID", "Name", "Password", "Phone", "Email"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 230, 800, 300); add(sp);

        int y=20;
        addField("ID", idTxt, y); y+=30;
        addField("Name", nameTxt, y); y+=30;
        addField("Password", passTxt, y); y+=30;
        addField("Phone", phoneTxt, y); y+=30;
        addField("Email", emailTxt, y); y+=30;

        idTxt.setEditable(false);

        addButtons();
        loadTeachers();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    idTxt.setText(model.getValueAt(row, 0).toString());
                    nameTxt.setText(model.getValueAt(row, 1).toString());
                    passTxt.setText(model.getValueAt(row, 2).toString());
                    phoneTxt.setText(model.getValueAt(row, 3).toString());
                    emailTxt.setText(model.getValueAt(row, 4).toString());
                }
            }
        });
    }

    private void addField(String lbl, JComponent field, int y) {
        JLabel l = new JLabel(lbl); l.setBounds(20,y,100,25); field.setBounds(130,y,150,25); add(l); add(field);
    }

    private void addButtons() {
        addBtn.setBounds(300,20,100,30); updateBtn.setBounds(300,60,100,30);
        deleteBtn.setBounds(300,100,100,30); loadBtn.setBounds(300,140,100,30);
        add(addBtn); add(updateBtn); add(deleteBtn); add(loadBtn);
        addBtn.addActionListener(this); updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this); loadBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        try(Connection con = DB.getConnection()) {
            if (e.getSource() == addBtn) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO teacher(name,password,phone,email) VALUES(?,?,?,?)");
                ps.setString(1,nameTxt.getText()); ps.setString(2,new String(passTxt.getPassword()));
                ps.setString(3, phoneTxt.getText()); ps.setString(4,emailTxt.getText());
                ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Teacher Added!"); loadTeachers();
            } else if (e.getSource() == updateBtn) {
                if (idTxt.getText().isEmpty()) { JOptionPane.showMessageDialog(this,"Select a teacher!"); return; }
                PreparedStatement ps = con.prepareStatement("UPDATE teacher SET name=?, password=?, phone=?, email=? WHERE teacher_id=?");
                ps.setString(1,nameTxt.getText()); ps.setString(2,new String(passTxt.getPassword()));
                ps.setString(3, phoneTxt.getText()); ps.setString(4,emailTxt.getText());
                ps.setInt(5,Integer.parseInt(idTxt.getText()));
                ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Teacher Updated!"); loadTeachers();
            } else if (e.getSource() == deleteBtn) {
                if (idTxt.getText().isEmpty()) { JOptionPane.showMessageDialog(this,"Select a teacher!"); return; }
                int confirm = JOptionPane.showConfirmDialog(this,"Are you sure?","Confirm Delete",JOptionPane.YES_NO_OPTION);
                if(confirm==JOptionPane.YES_OPTION) {
                    PreparedStatement ps = con.prepareStatement("DELETE FROM teacher WHERE teacher_id=?");
                    ps.setInt(1,Integer.parseInt(idTxt.getText())); ps.executeUpdate();
                    JOptionPane.showMessageDialog(this,"Teacher Deleted!"); loadTeachers();
                }
            } else if (e.getSource()==loadBtn) { loadTeachers(); }
        } catch(Exception ex){ ex.printStackTrace(); }
    }

    private void loadTeachers() {
        try(Connection con = DB.getConnection()) {
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM teacher");
            while(rs.next()) {
                model.addRow(new Object[]{rs.getInt("teacher_id"), rs.getString("name"), rs.getString("password"),
                        rs.getString("phone"), rs.getString("email")});
            }
        } catch(Exception ex){ ex.printStackTrace(); }
    }
}
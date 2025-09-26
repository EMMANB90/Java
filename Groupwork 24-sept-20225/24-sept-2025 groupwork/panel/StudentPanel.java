

package com.panel;

import javax.swing.*; import javax.swing.table.DefaultTableModel;
import java.awt.event.*; import java.sql.*;
import com.util.DB;

public class StudentPanel extends JPanel implements ActionListener {

    JTextField idTxt = new JTextField(), nameTxt = new JTextField(), phoneTxt = new JTextField(), emailTxt = new JTextField();
    JPasswordField passTxt = new JPasswordField();
    JComboBox<String> courseCmb = new JComboBox<>();
    JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"),
            deleteBtn = new JButton("Delete"), loadBtn = new JButton("Load");
    JTable table; DefaultTableModel model;

    public StudentPanel() {
        setLayout(null);
        String[] cols = {"ID","Name","Password","Phone","Email","Course"};
        model = new DefaultTableModel(cols,0); table = new JTable(model);
        JScrollPane sp = new JScrollPane(table); sp.setBounds(20,230,800,300); add(sp);

        int y=20;
        addField("ID",idTxt,y); y+=30;
        addField("Name",nameTxt,y); y+=30;
        addField("Password",passTxt,y); y+=30;
        addField("Phone",phoneTxt,y); y+=30;
        addField("Email",emailTxt,y); y+=30;
        addComboField("Course",courseCmb,y);

        idTxt.setEditable(false);
        addButtons(); loadCourses(); loadStudents();

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row = table.getSelectedRow();
                if(row>=0){
                    idTxt.setText(model.getValueAt(row,0).toString());
                    nameTxt.setText(model.getValueAt(row,1).toString());
                    passTxt.setText(model.getValueAt(row,2).toString());
                    phoneTxt.setText(model.getValueAt(row,3).toString());
                    emailTxt.setText(model.getValueAt(row,4).toString());
                    courseCmb.setSelectedItem(model.getValueAt(row,5).toString());
                }
            }
        });
    }

    private void addField(String lbl,JTextField field,int y){ JLabel l=new JLabel(lbl); l.setBounds(20,y,100,25); field.setBounds(130,y,150,25); add(l); add(field);}
    private void addComboField(String lbl,JComboBox<String> combo,int y){ JLabel l=new JLabel(lbl); l.setBounds(20,y,100,25); combo.setBounds(130,y,150,25); add(l); add(combo);}
    private void addButtons(){ addBtn.setBounds(300,20,100,30); updateBtn.setBounds(300,60,100,30); deleteBtn.setBounds(300,100,100,30); loadBtn.setBounds(300,140,100,30); add(addBtn); add(updateBtn); add(deleteBtn); add(loadBtn); addBtn.addActionListener(this); updateBtn.addActionListener(this); deleteBtn.addActionListener(this); loadBtn.addActionListener(this);}
    private void loadCourses(){ try(Connection con = DB.getConnection()){ ResultSet rs = con.createStatement().executeQuery("SELECT course_name FROM course"); courseCmb.removeAllItems(); while(rs.next()){ courseCmb.addItem(rs.getString("course_name")); } } catch(Exception ex){ ex.printStackTrace(); }}
    public void actionPerformed(ActionEvent e){
        try(Connection con = DB.getConnection()){
            if(e.getSource()==addBtn){
                PreparedStatement ps = con.prepareStatement("INSERT INTO student(name,password,phone,email,course) VALUES(?,?,?,?,?)");
                ps.setString(1,nameTxt.getText()); ps.setString(2,new String(passTxt.getPassword())); ps.setString(3,phoneTxt.getText()); ps.setString(4,emailTxt.getText()); ps.setString(5,courseCmb.getSelectedItem().toString());
                ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Student Added!"); loadStudents();
            } else if(e.getSource()==updateBtn){
                if(idTxt.getText().isEmpty()){ JOptionPane.showMessageDialog(this,"Select a student!"); return; }
                PreparedStatement ps = con.prepareStatement("UPDATE student SET name=?, password=?, phone=?, email=?, course=? WHERE student_id=?");
                ps.setString(1,nameTxt.getText()); ps.setString(2,new String(passTxt.getPassword())); ps.setString(3,phoneTxt.getText()); ps.setString(4,emailTxt.getText()); ps.setString(5,courseCmb.getSelectedItem().toString());
                ps.setInt(6,Integer.parseInt(idTxt.getText())); ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Student Updated!"); loadStudents();
            } else if(e.getSource()==deleteBtn){
                if(idTxt.getText().isEmpty()){ JOptionPane.showMessageDialog(this,"Select a student!"); return; }
                int confirm = JOptionPane.showConfirmDialog(this,"Are you sure?","Confirm Delete",JOptionPane.YES_NO_OPTION);
                if(confirm==JOptionPane.YES_OPTION){
                    PreparedStatement ps = con.prepareStatement("DELETE FROM student WHERE student_id=?");
                    ps.setInt(1,Integer.parseInt(idTxt.getText())); ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Student Deleted!"); loadStudents();
                }
            } else if(e.getSource()==loadBtn){ loadStudents(); }
        } catch(Exception ex){ ex.printStackTrace(); }
    }

    private void loadStudents(){
        try(Connection con = DB.getConnection()){
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM student");
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("student_id"), rs.getString("name"), rs.getString("password"),
                        rs.getString("phone"), rs.getString("email"), rs.getString("course")});
            }
        } catch(Exception ex){ ex.printStackTrace(); }
    }
}
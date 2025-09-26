

package com.panel;

import javax.swing.*; import javax.swing.table.DefaultTableModel;
import java.awt.event.*; import java.sql.*;
import com.util.DB;

public class MarksPanel extends JPanel implements ActionListener {

    JTextField idTxt = new JTextField(), studentTxt = new JTextField(), courseTxt = new JTextField(), marksTxt = new JTextField();
    JButton addBtn = new JButton("Add"), updateBtn = new JButton("Update"), deleteBtn = new JButton("Delete"), loadBtn = new JButton("Load");
    JTable table; DefaultTableModel model;

    public MarksPanel(){
        setLayout(null);
        String[] cols = {"ID","Student","Course","Marks"};
        model = new DefaultTableModel(cols,0); table = new JTable(model);
        JScrollPane sp = new JScrollPane(table); sp.setBounds(20,230,800,300); add(sp);

        int y=20;
        addField("ID",idTxt,y); y+=30;
        addField("Student",studentTxt,y); y+=30;
        addField("Course",courseTxt,y); y+=30;
        addField("Marks",marksTxt,y);

        idTxt.setEditable(false);
        addButtons(); loadMarks();

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row = table.getSelectedRow();
                if(row>=0){
                    idTxt.setText(model.getValueAt(row,0).toString());
                    studentTxt.setText(model.getValueAt(row,1).toString());
                    courseTxt.setText(model.getValueAt(row,2).toString());
                    marksTxt.setText(model.getValueAt(row,3).toString());
                }
            }
        });
    }

    private void addField(String lbl,JTextField field,int y){ JLabel l=new JLabel(lbl); l.setBounds(20,y,100,25); field.setBounds(130,y,150,25); add(l); add(field);}
    private void addButtons(){ addBtn.setBounds(300,20,100,30); updateBtn.setBounds(300,60,100,30); deleteBtn.setBounds(300,100,100,30); loadBtn.setBounds(300,140,100,30); add(addBtn); add(updateBtn); add(deleteBtn); add(loadBtn); addBtn.addActionListener(this); updateBtn.addActionListener(this); deleteBtn.addActionListener(this); loadBtn.addActionListener(this);}

    public void actionPerformed(ActionEvent e){
        try(Connection con = DB.getConnection()){
            if(e.getSource()==addBtn){
                PreparedStatement ps = con.prepareStatement("INSERT INTO marks(student,course,marks) VALUES(?,?,?)");
                ps.setString(1,studentTxt.getText()); ps.setString(2,courseTxt.getText()); ps.setString(3,marksTxt.getText());
                ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Marks Added!"); loadMarks();
            } else if(e.getSource()==updateBtn){
                if(idTxt.getText().isEmpty()){ JOptionPane.showMessageDialog(this,"Select a record!"); return; }
                PreparedStatement ps = con.prepareStatement("UPDATE marks SET student=?, course=?, marks=? WHERE marks_id=?");
                ps.setString(1,studentTxt.getText()); ps.setString(2,courseTxt.getText()); ps.setString(3,marksTxt.getText());
                ps.setInt(4,Integer.parseInt(idTxt.getText())); ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Marks Updated!"); loadMarks();
            } else if(e.getSource()==deleteBtn){
                if(idTxt.getText().isEmpty()){ JOptionPane.showMessageDialog(this,"Select a record!"); return; }
                int confirm = JOptionPane.showConfirmDialog(this,"Are you sure?","Confirm Delete",JOptionPane.YES_NO_OPTION);
                if(confirm==JOptionPane.YES_OPTION){
                    PreparedStatement ps = con.prepareStatement("DELETE FROM marks WHERE marks_id=?");
                    ps.setInt(1,Integer.parseInt(idTxt.getText())); ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Marks Deleted!"); loadMarks();
                }
            } else if(e.getSource()==loadBtn){ loadMarks(); }
        } catch(Exception ex){ ex.printStackTrace(); }
    }

    private void loadMarks(){
        try(Connection con = DB.getConnection()){
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM marks");
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("marks_id"), rs.getString("student"), rs.getString("course"), rs.getString("marks")});
            }
        } catch(Exception ex){ ex.printStackTrace(); }
    }
}
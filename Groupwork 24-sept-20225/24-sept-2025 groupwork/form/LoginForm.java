package com.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import com.util.DB;


public class LoginForm extends JFrame implements ActionListener{
	JTextField userTxt=new JTextField("Enter Username");
	JPasswordField passTxt=new JPasswordField("Password");
	JButton loginbtn=new JButton("Login");
	JButton cancelbtn=new JButton("Cancel");
	
	//constructor
	public LoginForm(){
		setTitle("LOGIN FORM");
		setBounds(100,100,300,200);
		setLayout(null);
		userTxt.setBounds(50,30,120,25);
		passTxt.setBounds(50,70,120,25);
		loginbtn.setBounds(30,120,100,30);
		cancelbtn.setBounds(150,120,100,30);
		add(userTxt);
		add(passTxt);
		add(loginbtn);
		add(cancelbtn);
		loginbtn.addActionListener(this);
		cancelbtn.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
//
//	private void setDefaultCloseOperation(String string) {
//		// TODO Auto-generated method stub
//		
//	}

	public void actionPerformed(ActionEvent e) {
      try(Connection con=DB.getConnection()){
    	  String sql="Select * FROM user WHERE username=?"+" AND password=?";
    	  PreparedStatement ps=con.prepareCall(sql);
    	  ps.setString(1, userTxt.getText());
    	  ps.setString(2, new String(passTxt.getPassword()));
    	  ResultSet rs=ps.executeQuery();
    	  if(rs.next()){
    		  String role=rs.getString("role");
    		  dispose();
    		  new SMIS(role,rs.getInt("userid"));
    	  }
    	  else{
    		  JOptionPane.showMessageDialog(this, "Invalid login");
    	  }
      }
      catch(Exception ex){
    	  ex.printStackTrace();
      }

	}


}
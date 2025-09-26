package com.form;

import java.awt.BorderLayout;

import javax.swing.*;

import com.panel.*;

public class SMIS extends JFrame {
	JTabbedPane tabs=new JTabbedPane();
	//constructor
	public SMIS (String role,int userid){
		setTitle("SchoolManagement System");
		setSize(900,600);
		setLayout(new BorderLayout ());
		if(role.equalsIgnoreCase("admin")){
			tabs.add("users", new UserPanel());
			tabs.add("teachers",new TeacherPanel());
			tabs.add("courses", new CoursePanel());
			tabs.add("Students",new StudentPanel());
			tabs.add("marks", new MarksPanel());
		}else if(role.equalsIgnoreCase("teacher")){
			tabs.add("courses",new CoursePanel());
			tabs.add("marks", new MarksPanel());
			
		}
		else if(role.equalsIgnoreCase("student")){
			tabs.add("my marks", new MarksPanel());
			
		}
		add(tabs,BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}

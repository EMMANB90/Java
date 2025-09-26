package com.form;


	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPasswordField;
	import javax.swing.JTextField;
	import javax.swing.JOptionPane;

	public class Registrationform implements ActionListener {

	    JFrame frame;
	    JLabel nameLabel = new JLabel("Name");
	    JLabel usernameLabel = new JLabel("Username");
	    JLabel passwordLabel = new JLabel("Password");

	    JTextField nameField = new JTextField();
	    JTextField usernameField = new JTextField();
	    JPasswordField passwordField = new JPasswordField();

	    JButton register = new JButton("Register");
	    JButton reset = new JButton("Reset");

	    // Constructor
	    public Registrationform() {
	        createWindow();
	        setLocationAndSize();
	        addComponentsToFrame();
	        addActionEvent();
	    }

	    private void createWindow() {
	        frame = new JFrame();
	        frame.setTitle("User Registration");
	        frame.setBounds(100, 100, 400, 250);
	        frame.getContentPane().setBackground(Color.lightGray);
	        frame.getContentPane().setLayout(null);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	    }

	    private void setLocationAndSize() {
	        nameLabel.setBounds(30, 30, 100, 30);
	        nameField.setBounds(150, 30, 180, 30);

	        usernameLabel.setBounds(30, 70, 100, 30);
	        usernameField.setBounds(150, 70, 180, 30);

	        passwordLabel.setBounds(30, 110, 100, 30);
	        passwordField.setBounds(150, 110, 180, 30);

	        register.setBounds(80, 160, 100, 30);
	        reset.setBounds(200, 160, 100, 30);
	    }

	    private void addComponentsToFrame() {
	        frame.add(nameLabel);
	        frame.add(nameField);
	        frame.add(usernameLabel);
	        frame.add(usernameField);
	        frame.add(passwordLabel);
	        frame.add(passwordField);
	        frame.add(register);
	        frame.add(reset);
	    }

	    private void addActionEvent() {
	        register.addActionListener(this);
	        reset.addActionListener(this);
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == register) {
	            String name = nameField.getText();
	            String username = usernameField.getText();
	            String password = new String(passwordField.getPassword());

	            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
	                JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(frame,
	                        "Registration Successful!\nName: " + name + "\nUsername: " + username,
	                        "Success",
	                        JOptionPane.INFORMATION_MESSAGE);
	            }

	        } else if (e.getSource() == reset) {
	            nameField.setText("");
	            usernameField.setText("");
	            passwordField.setText("");
	        }
	    }

}
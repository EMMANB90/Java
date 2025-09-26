package com.loan;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Loancalculator implements ActionListener {

    JFrame frame;
    JLabel amountLabel = new JLabel("Amount requested");
    JLabel durationLabel = new JLabel("Duration (years)");
    JLabel totalLabel = new JLabel("Total to return");

    JTextField amountField = new JTextField();
    JTextField durationField = new JTextField();
    JTextField totalField = new JTextField();

    JButton calculate = new JButton("Calculate");

    // Number formatter with spaces
    DecimalFormat formatter = new DecimalFormat("#,###");

    // Constructor
    public Loancalculator() {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        setDefaultValues();
        addActionEvent();
    }

    private void createWindow() {
        frame = new JFrame();
        frame.setTitle("Loan Calculator");
        frame.setBounds(100, 100, 350, 250);
        frame.getContentPane().setBackground(Color.lightGray);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private void setLocationAndSize() {
        amountLabel.setBounds(20, 20, 150, 30);
        amountField.setBounds(180, 20, 120, 30);

        durationLabel.setBounds(20, 60, 150, 30);
        durationField.setBounds(180, 60, 120, 30);

        totalLabel.setBounds(20, 100, 150, 30);
        totalField.setBounds(180, 100, 120, 30);
        totalField.setEditable(false);

        calculate.setBounds(100, 150, 120, 30);
    }

    private void addComponentsToFrame() {
        frame.add(amountLabel);
        frame.add(amountField);
        frame.add(durationLabel);
        frame.add(durationField);
        frame.add(totalLabel);
        frame.add(totalField);
        frame.add(calculate);
    }

    private void setDefaultValues() {
        // Default values as in your picture
        amountField.setText("2 000 000");
        durationField.setText("1");
        totalField.setText("2 012 000");
    }

    private void addActionEvent() {
        calculate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculate) {
            try {
                // Remove spaces before parsing
                double amount = Double.parseDouble(amountField.getText().replace(" ", ""));
                int years = Integer.parseInt(durationField.getText().replace(" ", ""));

                double total = amount + (amount * 0.006 * years);

                // Format numbers with spaces
                amountField.setText(formatter.format(amount).replace(",", " "));
                totalField.setText(formatter.format(total).replace(",", " "));
            } catch (Exception ex) {
                totalField.setText("Invalid input");
            }
        }
    }

    public static void main(String[] args) {
        new Loancalculator();
    }
}
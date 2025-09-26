package com.casestudy8;

public class ATM {
    private double balance;
    private final int pin;

    public ATM(int pin, double initialBalance) {
        this.pin = pin;
        this.balance = initialBalance;
    }

    public boolean authenticate(int inputPin) {
        return inputPin == pin;
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " | New Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient Funds");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        }
    }

    public double getBalance() {
        return balance;
    }
}

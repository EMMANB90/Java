package com.casestudy1;

public class BankAccount {
    private long accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(long accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public void deposit(double cash){
        this.balance =this.balance+cash;
        System.out.println("you have deposited "+cash +" and the new balance is "+this.balance);

    }
    public void withdraw (double cash){
        if (this.balance>cash) {
            this.balance = this.balance - cash;
            System.out.println("you have withdrawn "+cash +" and the new balance is "+this.balance);
            if (this.balance<1000){
                System.out.println("Low Balance!");
            }
        }
        else {
            System.out.println("Insufficient Funds!");
        }
    }
    public void displayAccount(){
        System.out.println("Account: " + accountNumber +
                " | Holder: " + holderName +
                " | Balance: " + balance);
    }

}

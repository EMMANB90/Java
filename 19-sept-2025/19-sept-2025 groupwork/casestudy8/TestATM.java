package com.casestudy8;

public class TestATM {
    public static void main(String[] args) {
        ATM myATM = new ATM(1234, 0);

        int enteredPin = 1234;

        if (myATM.authenticate(enteredPin)) {
            myATM.deposit(10000);
            myATM.withdraw(3000);
            System.out.println("Final Balance: " + myATM.getBalance());
        } else {
            System.out.println("Incorrect PIN");
        }
    }
}

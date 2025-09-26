package com.casestudy1;

public class TestBankAccount {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(123456787,"Mugisha",0);
        BankAccount account2 = new BankAccount(343223132,"Mucyo",0);

        account1.deposit(5000);
        account1.withdraw(2000);

        account1.withdraw(2500);
        account1.withdraw(1000);

        account1.displayAccount();
        account2.displayAccount();






    }
}

package com.casestudy3;

public class TestEmployee {
    public static void main(String[] args) {
        Employee emp1 = new FullTimeEmployee("E001", "Mucyo", 50000);
        Employee emp2 = new PartTimeEmployee("E002", "Muhire", 120, 300);

        System.out.println("------------Salary details-----------------");
        emp1.displaySalary();
        emp2.displaySalary();
        System.out.println("----------------------------------------");

        double totalExpenditure = emp1.calculateSalary() + emp2.calculateSalary();
        System.out.println("Total Salary Expenditure: " + totalExpenditure);
    }
}

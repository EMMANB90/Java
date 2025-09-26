package com.casestudy3;

public abstract class Employee {
    protected String id;
    protected String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract double calculateSalary();

    public void displaySalary() {
        double salary = calculateSalary();
        System.out.println("ID: " + id + " | Name: " + name + " | Salary: " + salary);
        if (salary > 60000) {
            System.out.println("High Earner");
        }
    }
}

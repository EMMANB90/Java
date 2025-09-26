package com.casestudy3;

public class FullTimeEmployee extends Employee {
    private double baseSalary;

    public FullTimeEmployee(String id, String name, double baseSalary) {
        super(id, name);
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + (0.2 * baseSalary);
    }
}

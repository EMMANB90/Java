package com.casestudy7;

public abstract class Vehicle {
    protected String registrationNumber;
    protected double speed;

    public Vehicle(String registrationNumber, double speed) {
        this.registrationNumber = registrationNumber;
        this.speed = speed;
    }

    public abstract void displayDetails();
}

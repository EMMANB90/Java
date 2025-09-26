package com.casestudy7;

public class Car extends Vehicle {
    private double fuelEfficiency;

    public Car(String registrationNumber, double speed, double fuelEfficiency) {
        super(registrationNumber, speed);
        this.fuelEfficiency = fuelEfficiency;
    }

    @Override
    public void displayDetails() {
        System.out.println("Car | RegNo: " + registrationNumber +
                " | Speed: " + speed + " km/h" +
                " | Fuel Efficiency: " + fuelEfficiency + " km/l");
    }
}

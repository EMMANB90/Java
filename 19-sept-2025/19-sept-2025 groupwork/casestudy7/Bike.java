package com.casestudy7;

public class Bike extends Vehicle {
    private double fuelEfficiency;

    public Bike(String registrationNumber, double speed, double fuelEfficiency) {
        super(registrationNumber, speed);
        this.fuelEfficiency = fuelEfficiency;
    }

    @Override
    public void displayDetails() {
        System.out.println("Bike | RegNo: " + registrationNumber +
                " | Speed: " + speed + " km/h" +
                " | Fuel Efficiency: " + fuelEfficiency + " km/l");
    }
}

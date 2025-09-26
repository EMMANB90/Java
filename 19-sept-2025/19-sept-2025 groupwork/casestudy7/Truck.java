package com.casestudy7;

public class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String registrationNumber, double speed, double loadCapacity) {
        super(registrationNumber, speed);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("Truck | RegNo: " + registrationNumber +
                " | Speed: " + speed + " km/h" +
                " | Load Capacity: " + loadCapacity + " tons");
    }
}

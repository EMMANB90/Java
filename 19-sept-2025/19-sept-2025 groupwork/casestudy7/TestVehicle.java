package com.casestudy7;

public class TestVehicle {
    public static void main(String[] args) {
        Vehicle v1 = new Car("CAR123", 180, 15);
        Vehicle v2 = new Bike("BIKE456", 120, 40);
        Vehicle v3 = new Truck("TRK789", 90, 10);

        Vehicle[] vehicles = {v1, v2, v3};
        System.out.println("\nHere Vehicle details:");
        for (Vehicle v : vehicles) {
            v.displayDetails();
        }
    }
}

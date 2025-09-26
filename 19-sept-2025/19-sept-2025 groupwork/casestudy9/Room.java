package com.casestudy9;

public class Room {
    private String roomNumber;
    private String type;
    private double pricePerNight;

    public Room(String roomNumber, String type, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public double calculateCost(int nights) {
        double total = pricePerNight * nights;
        if (nights > 5) {
            total *= 0.85;
        }
        return total;
    }

    public void displayRoom() {
        System.out.println("Room No: " + roomNumber +
                " || Type: " + type +
                " || Price/Night: " + pricePerNight);
    }
}

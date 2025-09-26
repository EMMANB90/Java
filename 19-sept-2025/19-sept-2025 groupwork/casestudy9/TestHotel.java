package com.casestudy9;

public class TestHotel {
    public static void main(String[] args) {
        Room r1 = new Room("101", "Standard", 5000);
        Room r2 = new Room("102", "Deluxe", 8000);
        Room r3 = new Room("103", "Suite", 15000);

        r1.displayRoom();
        r2.displayRoom();
        r3.displayRoom();

        int nights = 6;
        double totalBill = r2.calculateCost(nights);

        System.out.println("Guest stayed " + nights + " nights in Deluxe Room");
        System.out.println("Total Bill: " + totalBill);
    }
}

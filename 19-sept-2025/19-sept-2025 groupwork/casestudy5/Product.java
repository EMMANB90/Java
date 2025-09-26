package com.casestudy5;

public class Product {
    private String productId;
    private String name;
    private double price;
    private int quantity;

    public Product(String productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double calculateCost() {
        return price * quantity;
    }

    public void displayProduct() {
        System.out.println("ID: " + productId + " | Name: " + name +
                " | Price: " + price + " | Quantity: " + quantity +
                " | Cost: " + calculateCost());
    }
}

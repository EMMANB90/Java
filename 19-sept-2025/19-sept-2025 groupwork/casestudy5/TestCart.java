package com.casestudy5;

public class TestCart {
    public static void main(String[] args) {
        Product p1 = new Product("P001", "Laptop", 40000, 1);
        Product p2 = new Product("P002", "Mouse", 1000, 2);
        Product p3 = new Product("P003", "Keyboard", 3000, 1);

        Product[] products = {p1, p2, p3};

        double total = 0;
        for (Product p : products) {
            p.displayProduct();
            total += p.calculateCost();
        }

        if (total > 10000) {
            double discounted = total * 0.9;
            System.out.println("Total Bill (after discount): " + discounted);
            System.out.println("Discount of 10% Applied");
        } else {
            System.out.println("Total Bill: " + total);
            System.out.println("No Discount");
        }
    }
}

package all;

import java.util.ArrayList;
import java.util.Scanner;

class Item {
    String name;
    double price;
    int quantity;
    double subtotal;

    Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = price * quantity;
    }
}

public class alljava{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> items = new ArrayList<>();
        double totalBill = 0;

        System.out.print("Enter number of different items purchased: ");
        int numItems = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < numItems; i++) {
            System.out.println("\nItem " + (i + 1) + ":");
            System.out.print("Enter item name: ");
            String name = scanner.nextLine();

            System.out.print("Enter price per unit: ");
            double price = scanner.nextDouble();

            System.out.print("Enter quantity purchased: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Item item = new Item(name, price, quantity);
            items.add(item);
            totalBill += item.subtotal;
        }

        double discount = 0;
        if (totalBill > 50000) {
            discount = totalBill * 0.05;
        }

        double finalAmount = totalBill - discount;

        // Print receipt
        System.out.println("\n-----------------------------------------");
        System.out.println("RECEIPT");
        System.out.println("------------------------------------------");
        for (Item item : items) {
            System.out.printf("%s - Qty: %d @ %.2f each → Subtotal: %.2f\n",
                    item.name, item.quantity, item.price, item.subtotal);
        }

        System.out.println("Grand Total:"+ totalBill);
        System.out.println("Discount:"+ discount);
        System.out.println("Final Amount Payable:"+finalAmount);
        System.out.println("--------------------------------------------------------");
    }
}
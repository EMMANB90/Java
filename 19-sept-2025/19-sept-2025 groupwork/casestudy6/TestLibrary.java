package com.casestudy6;

public class TestLibrary {
    public static void main(String[] args) {
        Book b1 = new Book("Java Basics", "Mucyo");
        Book b2 = new Book("OOP Concepts", "Muhire");
        Book b3 = new Book("Data Structures", "Mukiza");

        b1.borrowBook();
        b2.borrowBook();
        b2.borrowBook();
        b1.returnBook();
        b1.returnBook();
        Book[] books = {b1, b2, b3};
        System.out.println("\nAvailable Books:");
        for (Book b : books) {
            if (b.isAvailable()) {
                b.displayBook();
            }
        }
    }
}

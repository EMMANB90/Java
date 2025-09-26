package com.casestudy6;

public class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public void borrowBook() {
        if (available) {
            available = false;
            System.out.println(title + " borrowed successfully.");
        } else {
            System.out.println(title + " is not available.");
        }
    }

    public void returnBook() {
        if (!available) {
            available = true;
            System.out.println(title + " returned successfully.");
        } else {
            System.out.println(title + " was not borrowed.");
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public void displayBook() {
        System.out.println("Title: " + title +
                " | Author: " + author +
                " | Available: " + available);
    }
}

package com.casestudy4;

public abstract class Shape {
    public abstract double area();

    public void displayArea() {
        System.out.println("Area: " + area());
    }
}

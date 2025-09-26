package com.casestudy4;

public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
    @Override
    public void displayArea() {
        System.out.println("Area of rectangle: " + area());
    }
}

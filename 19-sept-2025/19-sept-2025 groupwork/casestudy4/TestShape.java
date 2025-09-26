package com.casestudy4;

public class TestShape {
    public static void main(String[] args) {
        Shape s1 = new Circle(7);
        Shape s2 = new Rectangle(10, 5);
        Shape s3 = new Triangle(8, 6);

        Shape[] shapes = {s1, s2, s3};

        double totalArea = 0;
        System.out.println("-------------Shapes Area------------------");
        for (Shape s : shapes) {
            s.displayArea();
            totalArea += s.area();
        }
        System.out.println("-----------------------------------");

        System.out.println("Total Area of all shapes is: " + totalArea);
    }
}

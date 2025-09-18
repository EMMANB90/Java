package groupwork2;

import java.util.Scanner;

public class testcircle {
		 public static void main(String[] args) {
		     Scanner sc = new Scanner(System.in);

		     System.out.print("Enter the radius of the circle: ");
		     double radius = sc.nextDouble(); 

		     Circle circle = new Circle(radius);

		     System.out.println("Choose an option:");
		     System.out.println("1. Calculate Area");
		     System.out.println("2. Calculate Circumference");
		     System.out.print("Enter your choice (1 or 2): ");
		     int choice = sc.nextInt();
		     switch (choice) {
		         case 1:
		             System.out.println("Area of the circle: " + circle.calculateArea());
		             break;
		         case 2:
		             System.out.println("Circumference of the circle: " + circle.calculateCircumference());
		             break;
		         default:
		             System.out.println("Invalid choice! Please enter 1 or 2.");
		     }

		     sc.close(); 
		 }
		}


package gpwork4;

	import java.util.Scanner;

	// Rectangle class
	class Rectangle {
	    double length;
	    double width;

	    Rectangle(double l, double w) {
	        length = l;
	        width = w;
	    }

	    double calculateArea() {
	        return length * width;
	    }
	}

	// Circle class
	class Circle {
	    double radius;

	    Circle(double r) {
	        radius = r;
	    }

	    double calculateArea() {
	        return Math.PI * radius * radius;
	    }
	}

	public class rectcircle {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        System.out.println("Choose a shape to calculate area:");
	        System.out.println("1. Rectangle");
	        System.out.println("2. Circle");
	        System.out.print("Enter choice (1 or 2): ");
	        int choice = sc.nextInt();

	        switch (choice) {
	            case 1:
	                System.out.print("Enter length of rectangle: ");
	                double length = sc.nextDouble();
	                System.out.print("Enter width of rectangle: ");
	                double width = sc.nextDouble();
	                Rectangle rect = new Rectangle(length, width);
	                System.out.println("Area of rectangle: " + rect.calculateArea());
	                break;
	            case 2:
	                System.out.print("Enter radius of circle: ");
	                double radius = sc.nextDouble();
	                Circle circ = new Circle(radius);
	                System.out.println("Area of circle: " + circ.calculateArea());
	                break;
	            default:
	                System.out.println("Invalid choice!");
	        }

	        sc.close();
	    }
	}



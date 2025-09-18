package gpwork5;


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

	public class rectcircle2{
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        // Input rectangle dimensions
	        System.out.print("Enter length of rectangle: ");
	        double length = sc.nextDouble();
	        System.out.print("Enter width of rectangle: ");
	        double width = sc.nextDouble();
	        Rectangle rect = new Rectangle(length, width);

	        // Input circle radius
	        System.out.print("Enter radius of circle: ");
	        double radius = sc.nextDouble();
	        Circle circ = new Circle(radius);

	        // Compare areas
	        double rectArea = rect.calculateArea();
	        double circArea = circ.calculateArea();

	        if (rectArea > circArea) {
	            System.out.println("Rectangle has a larger area: " + rectArea);
	        } else if (circArea > rectArea) {
	            System.out.println("Circle has a larger area: " + circArea);
	        } else {
	            System.out.println("Both shapes have equal area: " + rectArea);
	        }

	        sc.close();
	    }
	}



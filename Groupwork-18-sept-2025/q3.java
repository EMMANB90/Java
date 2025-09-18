package gpwork3;

	import java.util.Scanner;

	// Rectangle class with length and width
	class Rectangle {
	    double length;
	    double width;

	    // Constructor
	    Rectangle(double l, double w) {
	        length = l;
	        width = w;
	    }

	    // Method to calculate area
	    double calculateArea() {
	        return length * width;
	    }
	}

	public class q3 {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        Rectangle[] rectangles = new Rectangle[3]; // Array to store 3 rectangles

	        // Loop to input 3 rectangles
	        for (int i = 0; i < 3; i++) {
	            System.out.println("Enter details for Rectangle " + (i + 1));
	            System.out.print("Length: ");
	            double length = sc.nextDouble();
	            System.out.print("Width: ");
	            double width = sc.nextDouble();

	            rectangles[i] = new Rectangle(length, width); // Create Rectangle object
	        }

	        // Loop to print areas
	        for (int i = 0; i < 3; i++) {
	            System.out.println("Area of Rectangle " + (i + 1) + ": " + rectangles[i].calculateArea());
	        }

	        sc.close();
	    }
	}

	



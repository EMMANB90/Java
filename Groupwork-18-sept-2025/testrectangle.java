package groupwork;

import java.util.Scanner;
public class testrectangle {
public static void main (String [] args) {
	Scanner sc=new Scanner(System.in);
	rectangle r=new rectangle();
	System.out.print("enter length: ");
	double length=sc.nextDouble();
	r.setLength(length);
	System.out.print("enter width: ");
	double width=sc.nextDouble();
	r.setWidth(width);
	if(length==width) {
		System.out.print("this is square");
	}
	else {
		r.setArea();
	System.out.println("rectangle area is: "+ r.getArea());
	}
	
}
}

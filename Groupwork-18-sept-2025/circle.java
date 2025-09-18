package groupwork2;

import java.util.Scanner; 


class Circle {
 double radius; 

 Circle(double r) {
     radius = r;
 }
 double calculateArea() {
     return Math.PI * radius * radius;
 }

 double calculateCircumference() {
     return 2 * Math.PI * radius;
 }
}



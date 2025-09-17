package all;
import java.util.Scanner;

public class q2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalStudents = 0;
        int passes = 0;
        int fails = 0;

        System.out.println("Enter student marks (0–100). Enter -1 to stop.");

        while (true) {
            System.out.print("Enter marks: ");
            int marks = scanner.nextInt();

            if (marks == -1) break;

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                continue;
            }

            totalStudents++;

            // Grade assignment
            if (marks >= 80) {
                System.out.println("Grade: A");
            } else if (marks >= 70) {
                System.out.println("Grade: B");
            } else if (marks >= 60) {
                System.out.println("Grade: C");
            } else if (marks >= 50) {
                System.out.println("Grade: D");
            } else {
                System.out.println("Grade: F");
            }

            // Pass/fail tracking
            if (marks >= 50) {
                passes++;
            } else {
                fails++;
            }
        }

        // Summary report
        System.out.println("\n📊 Summary Report");
        System.out.println("Total students: " + totalStudents);
        System.out.println("Passed: " + passes);
        System.out.println("Failed: " + fails);

        if (totalStudents > 0) {
            double passRate = ((double) passes / totalStudents) * 100;
            System.out.printf("Class Pass Rate: %.2f%%\n", passRate);
        } else {
            System.out.println("No student data entered.");
        }
    }
}
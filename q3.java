package all;
import java.util.Scanner;

public class q3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int MAX_DAYS = 30;
        int[] attendance = new int[MAX_DAYS];
        int dayCount = 0;

        System.out.print("Enter total number of students in the class: ");
        int classSize = scanner.nextInt();
        scanner.nextLine(); // consume newline
         String choice = null;

        do {
            if (dayCount >= MAX_DAYS) {
                System.out.println("Maximum number of days reached.");
                break;
            }

            System.out.print("Enter number of students present on Day " + (dayCount + 1) + ": ");
            int present = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (present < 0 || present > classSize) {
                System.out.println("Invalid input. Must be between 0 and " + classSize);
                continue;
            }

            attendance[dayCount] = present;
            dayCount++;

            System.out.print("Do you want to enter attendance for another day? (yes/no): ");
           choice = scanner.nextLine().trim().toLowerCase();
        } while (choice.equals("yes"));

        // Summary
        System.out.println("\n📅 Attendance Summary");
        int lowAttendanceDays = 0;
        int totalAttendance = 0;

        for (int i = 0; i < dayCount; i++) {
            System.out.printf("Day %d → Present: %d\n", i + 1, attendance[i]);
            totalAttendance += attendance[i];
            if (attendance[i] < classSize / 2.0) {
                lowAttendanceDays++;
            }
        }

        if (dayCount > 0) {
            double average = (double) totalAttendance / dayCount;
            double lowRate = ((double) lowAttendanceDays / dayCount) * 100;

            System.out.printf("\nAverage Attendance: %.2f\n", average);
            System.out.printf("Days with <50%% attendance: %d (%.2f%%)\n", lowAttendanceDays, lowRate);
        } else {
            System.out.println("No attendance data recorded.");
        }
    }
}
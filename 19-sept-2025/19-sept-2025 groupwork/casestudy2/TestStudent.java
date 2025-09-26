package com.casestudy2;

public class TestStudent {
    public static void main(String[] args) {
        Student s1 = new Student("S001", "Mucyo", new double[]{90, 85, 80});
        Student s2 = new Student("S002", "Muhire", new double[]{70, 65, 60});
        Student s3 = new Student("S003", "Mukiza", new double[]{40, 35, 45});

        System.out.println("All students overview:");
        s1.displayStudent();
        s2.displayStudent();
        s3.displayStudent();
        System.out.println("---------------------------------------------");

        Student[] students = {s1, s2, s3};
        double highestAvg = 0;
        for (Student s : students) {
            double avg = s.getAverage();
            if (avg > highestAvg)
                highestAvg = avg;

        }

        System.out.println("Highest Average within all students is : " + highestAvg);
    }
}

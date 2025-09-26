package com.casestudy2;

public class Student {
    private String id;
    private String name;
    private double[] marks;

    // Constructor
    public Student(String id, String name, double[] marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Method to compute average
    public double getAverage() {
        int sum = 0;
        for (double m : marks) {
            sum += m;
        }
        return (double) sum / marks.length;
    }

    // Method to return grade
    public String getGrade() {
        double avg = getAverage();
        if (avg >= 80) return "A";
        else if (avg >= 60) return "B";
        else if (avg >= 40) return "C";
        else return "Fail";
    }

    public void displayStudent() {
        System.out.println("ID: " + id +
                " | Name: " + name +
                " | Average: " + getAverage() +
                " | Grade: " + getGrade());
    }
}


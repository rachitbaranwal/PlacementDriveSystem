package com.placement.main;

import com.placement.model.Student;

public class Main {

    public static void main(String[] args) {

        // We are creating ONE Student object here
        // Think of this as: filling out one admission form
        Student student1 = new Student(1, "Rahul Sharma", "rahul@gmail.com", 8.5, "CSE");

        // Now let's print that student's details
        student1.displayStudent();

        // Let's create a second student object
        Student student2 = new Student(2, "Priya Mehta", "priya@gmail.com", 7.8, "ECE");
        student2.displayStudent();
    }
}
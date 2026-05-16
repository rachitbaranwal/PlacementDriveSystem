package com.placement.model;

public class Student {

    // These are called "fields" or "attributes"
    // They describe what data every Student object will hold
    int studentId;
    String name;
    String email;
    double cgpa;
    String branch;

    // This is a CONSTRUCTOR
    // It's a special method that runs when you CREATE a new Student object
    // It fills in all the fields at the time of creation
    public Student(int studentId, String name, String email, double cgpa, String branch) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
        this.branch = branch;
    }

    // This method prints one student's details neatly
    public void displayStudent() {
        System.out.println("-----------------------------");
        System.out.println("ID     : " + studentId);
        System.out.println("Name   : " + name);
        System.out.println("Email  : " + email);
        System.out.println("CGPA   : " + cgpa);
        System.out.println("Branch : " + branch);
        System.out.println("-----------------------------");
    }
}
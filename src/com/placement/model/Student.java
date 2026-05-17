package com.placement.model;

public class Student {

    // Added "public" in front of every field
    // This allows ANY class in ANY package to access these fields
    public int studentId;
    public String name;
    public String email;
    public double cgpa;
    public String branch;

    public Student(int studentId, String name, String email, double cgpa, String branch) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
        this.branch = branch;
    }

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
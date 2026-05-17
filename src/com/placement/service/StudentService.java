package com.placement.service;

import com.placement.model.Student;
import java.util.ArrayList;

public class StudentService {

    // This is our "database" for now — stores all students in memory
    // ArrayList<Student> means: a list that only holds Student objects
    private ArrayList<Student> studentList = new ArrayList<>();

    // We use this to auto-generate IDs: 1, 2, 3, 4...
    private int idCounter = 1;


    // ─────────────────────────────────────────
    // ADD STUDENT
    // ─────────────────────────────────────────
    public void addStudent(String name, String email, double cgpa, String branch) {

        // Create a new Student object using the data passed in
        Student newStudent = new Student(idCounter, name, email, cgpa, branch);

        // Add it to our list
        studentList.add(newStudent);

        // Increase counter so next student gets a different ID
        idCounter++;

        System.out.println("✅ Student added successfully! ID assigned: " + newStudent.studentId);
    }


    // ─────────────────────────────────────────
    // VIEW ALL STUDENTS
    // ─────────────────────────────────────────
    public void viewAllStudents() {

        // Check if the list is empty first
        if (studentList.isEmpty()) {
            System.out.println("⚠️  No students found. Please add students first.");
            return; // Stop the method here, don't go further
        }

        System.out.println("\n========== ALL STUDENTS ==========");

        // This loop goes through every student in the list one by one
        // "Student s" means: for each item in studentList, call it "s"
        for (Student s : studentList) {
            s.displayStudent(); // Call the display method we wrote earlier
        }
    }


    // ─────────────────────────────────────────
    // DELETE STUDENT
    // ─────────────────────────────────────────
    public void deleteStudent(int studentId) {

        // We'll search the list manually using a loop
        Student foundStudent = null;

        for (Student s : studentList) {
            if (s.studentId == studentId) {
                foundStudent = s; // Found it!
                break;            // Stop searching
            }
        }

        // If we found the student, remove them
        if (foundStudent != null) {
            studentList.remove(foundStudent);
            System.out.println("🗑️  Student with ID " + studentId + " deleted successfully.");
        } else {
            System.out.println("❌ No student found with ID: " + studentId);
        }
    }


    // ─────────────────────────────────────────
    // UPDATE STUDENT
    // ─────────────────────────────────────────
    public void updateStudent(int studentId, String newName, String newEmail,
                              double newCgpa, String newBranch) {

        for (Student s : studentList) {
            if (s.studentId == studentId) {

                // Update each field of the found student
                s.name   = newName;
                s.email  = newEmail;
                s.cgpa   = newCgpa;
                s.branch = newBranch;

                System.out.println("✏️  Student updated successfully!");
                return; // Done, exit the method
            }
        }

        System.out.println(" No student found with ID: " + studentId);
    }
}
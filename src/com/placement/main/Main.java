package com.placement.main;

import com.placement.db.DatabaseConnection;
import com.placement.service.StudentService;
import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Test connection first before anything else
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) {
            System.out.println(" Cannot start — Database connection failed!");
            return;
        }

        StudentService studentService = new StudentService();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║  PLACEMENT DRIVE MANAGEMENT SYSTEM   ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Add Student                      ║");
            System.out.println("║  2. View All Students                ║");
            System.out.println("║  3. Update Student                   ║");
            System.out.println("║  4. Delete Student                   ║");
            System.out.println("║  5. Exit                             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print(" Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: handleAddStudent(sc, studentService);    break;
                case 2: studentService.viewAllStudents();        break;
                case 3: handleUpdateStudent(sc, studentService); break;
                case 4: handleDeleteStudent(sc, studentService); break;
                case 5: System.out.println("\n Goodbye!");     break;
                default: System.out.println(" Invalid choice.");
            }
        }
        sc.close();
    }

    private static void handleAddStudent(Scanner sc, StudentService studentService) {
        System.out.println("\n─── Add New Student ───");
        System.out.print("Enter Name   : "); String name   = sc.nextLine();
        System.out.print("Enter Email  : "); String email  = sc.nextLine();
        System.out.print("Enter CGPA   : "); double cgpa   = sc.nextDouble(); sc.nextLine();
        System.out.print("Enter Branch : "); String branch = sc.nextLine();
        studentService.addStudent(name, email, cgpa, branch);
    }

    private static void handleUpdateStudent(Scanner sc, StudentService studentService) {
        studentService.viewAllStudents();
        System.out.println("\n─── Update Student ───");
        System.out.print("Enter Student ID to update: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter New Name   : "); String name   = sc.nextLine();
        System.out.print("Enter New Email  : "); String email  = sc.nextLine();
        System.out.print("Enter New CGPA   : "); double cgpa   = sc.nextDouble(); sc.nextLine();
        System.out.print("Enter New Branch : "); String branch = sc.nextLine();
        studentService.updateStudent(id, name, email, cgpa, branch);
    }

    private static void handleDeleteStudent(Scanner sc, StudentService studentService) {
        studentService.viewAllStudents();
        System.out.println("\n─── Delete Student ───");
        System.out.print("Enter Student ID to delete: "); int id = sc.nextInt(); sc.nextLine();
        studentService.deleteStudent(id);
    }
}
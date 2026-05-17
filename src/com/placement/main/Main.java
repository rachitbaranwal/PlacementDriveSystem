package com.placement.main;

import com.placement.service.StudentService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Create our service — this holds all student logic
        StudentService studentService = new StudentService();

        // Create scanner — this reads user input from keyboard
        Scanner sc = new Scanner(System.in);

        int choice = 0; // Stores the menu option user picks

        // Keep showing the menu until user picks Exit (5)
        while (choice != 5) {

            // ── Print the menu ──
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║  PLACEMENT DRIVE MANAGEMENT SYSTEM   ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Add Student                      ║");
            System.out.println("║  2. View All Students                ║");
            System.out.println("║  3. Update Student                   ║");
            System.out.println("║  4. Delete Student                   ║");
            System.out.println("║  5. Exit                             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("👉 Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // ← Clear leftover newline (explained above!)

            // ── Handle user's choice ──
            switch (choice) {

                case 1:
                    handleAddStudent(sc, studentService);
                    break;

                case 2:
                    studentService.viewAllStudents();
                    break;

                case 3:
                    handleUpdateStudent(sc, studentService);
                    break;

                case 4:
                    handleDeleteStudent(sc, studentService);
                    break;

                case 5:
                    System.out.println("\n👋 Goodbye! Exiting system...");
                    break;

                default:
                    System.out.println("⚠️  Invalid choice. Please enter 1 to 5.");
            }
        }

        sc.close(); // Always close Scanner when done
    }


    // ─────────────────────────────────────────
    // ADD STUDENT — collects input then calls service
    // ─────────────────────────────────────────
    private static void handleAddStudent(Scanner sc, StudentService studentService) {

        System.out.println("\n─── Add New Student ───");

        System.out.print("Enter Name   : ");
        String name = sc.nextLine();

        System.out.print("Enter Email  : ");
        String email = sc.nextLine();

        System.out.print("Enter CGPA   : ");
        double cgpa = sc.nextDouble();
        sc.nextLine(); // ← Clear leftover newline

        System.out.print("Enter Branch : ");
        String branch = sc.nextLine();

        // Pass all collected data to the service
        studentService.addStudent(name, email, cgpa, branch);
    }


    // ─────────────────────────────────────────
    // UPDATE STUDENT — collects new data then calls service
    // ─────────────────────────────────────────
    private static void handleUpdateStudent(Scanner sc, StudentService studentService) {

        // First show all students so user knows which ID to update
        studentService.viewAllStudents();

        System.out.println("\n─── Update Student ───");
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // ← Clear leftover newline

        System.out.print("Enter New Name   : ");
        String name = sc.nextLine();

        System.out.print("Enter New Email  : ");
        String email = sc.nextLine();

        System.out.print("Enter New CGPA   : ");
        double cgpa = sc.nextDouble();
        sc.nextLine(); // ← Clear leftover newline

        System.out.print("Enter New Branch : ");
        String branch = sc.nextLine();

        studentService.updateStudent(id, name, email, cgpa, branch);
    }


    // ─────────────────────────────────────────
    // DELETE STUDENT — asks for ID then calls service
    // ─────────────────────────────────────────
    private static void handleDeleteStudent(Scanner sc, StudentService studentService) {

        studentService.viewAllStudents();

        System.out.println("\n─── Delete Student ───");
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine(); // ← Clear leftover newline

        studentService.deleteStudent(id);
    }
}
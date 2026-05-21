package com.placement.main;

import com.placement.db.DatabaseConnection;
import com.placement.service.ApplicationService;
import com.placement.service.CompanyService;
import com.placement.service.StudentService;
import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Test connection first
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println(" Cannot start — Database connection failed!");
            return;
        }

        // Create all service objects
        StudentService     studentService     = new StudentService();
        CompanyService     companyService     = new CompanyService();
        ApplicationService applicationService = new ApplicationService();

        Scanner sc     = new Scanner(System.in);
        int     choice = 0;

        while (choice != 9) {

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║    PLACEMENT DRIVE MANAGEMENT SYSTEM     ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  --- STUDENT MENU ---                    ║");
            System.out.println("║  1. Add Student                          ║");
            System.out.println("║  2. View All Students                    ║");
            System.out.println("║  3. Update Student                       ║");
            System.out.println("║  4. Delete Student                       ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  --- COMPANY MENU ---                    ║");
            System.out.println("║  5. Add Company                          ║");
            System.out.println("║  6. View All Companies                   ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  --- APPLICATION MENU ---                ║");
            System.out.println("║  7. Apply Student to Company             ║");
            System.out.println("║  8. View All Applications                ║");
            System.out.println("║  9. Check Eligible Students              ║");
            System.out.println("║  10. Update Application Status           ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  0. Exit                                 ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print(" Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ── Student Operations ──
                case 1:  handleAddStudent(sc, studentService);         break;
                case 2:  studentService.viewAllStudents();             break;
                case 3:  handleUpdateStudent(sc, studentService);      break;
                case 4:  handleDeleteStudent(sc, studentService);      break;

                // ── Company Operations ──
                case 5:  handleAddCompany(sc, companyService);         break;
                case 6:  companyService.viewAllCompanies();            break;

                // ── Application Operations ──
                case 7:  handleApplyToCompany(sc, applicationService,
                        studentService, companyService);          break;
                case 8:  applicationService.viewAllApplications();     break;
                case 9:  handleEligibleStudents(sc, companyService);   break;
                case 10: handleUpdateStatus(sc, applicationService);   break;

                case 0:
                    System.out.println("\n Goodbye! Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("  Invalid choice. Enter 0-10.");
            }
        }
    }

    private static void handleAddStudent(Scanner sc, StudentService ss) {
        System.out.println("\n─── Add New Student ───");
        System.out.print("Enter Name   : "); String name   = sc.nextLine();
        System.out.print("Enter Email  : "); String email  = sc.nextLine();
        System.out.print("Enter CGPA   : "); double cgpa   = sc.nextDouble(); sc.nextLine();
        System.out.print("Enter Branch : "); String branch = sc.nextLine();
        ss.addStudent(name, email, cgpa, branch);
    }

    private static void handleUpdateStudent(Scanner sc, StudentService ss) {
        ss.viewAllStudents();
        System.out.println("\n─── Update Student ───");
        System.out.print("Enter Student ID to update : "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter New Name             : "); String name   = sc.nextLine();
        System.out.print("Enter New Email            : "); String email  = sc.nextLine();
        System.out.print("Enter New CGPA             : "); double cgpa   = sc.nextDouble(); sc.nextLine();
        System.out.print("Enter New Branch           : "); String branch = sc.nextLine();
        ss.updateStudent(id, name, email, cgpa, branch);
    }

    private static void handleDeleteStudent(Scanner sc, StudentService ss) {
        ss.viewAllStudents();
        System.out.println("\n─── Delete Student ───");
        System.out.print("Enter Student ID to delete : ");
        int id = sc.nextInt(); sc.nextLine();
        ss.deleteStudent(id);
    }

    private static void handleAddCompany(Scanner sc, CompanyService cs) {
        System.out.println("\n─── Add New Company ───");
        System.out.print("Enter Company Name : "); String name    = sc.nextLine();
        System.out.print("Enter Min CGPA     : "); double minCgpa = sc.nextDouble(); sc.nextLine();
        System.out.print("Enter Role         : "); String role    = sc.nextLine();
        System.out.print("Enter CTC (LPA)    : "); double ctc     = sc.nextDouble(); sc.nextLine();
        cs.addCompany(name, minCgpa, role, ctc);
    }


    private static void handleApplyToCompany(Scanner sc, ApplicationService as,
                                             StudentService ss, CompanyService cs) {
        ss.viewAllStudents();
        cs.viewAllCompanies();
        System.out.println("\n─── Apply Student to Company ───");
        System.out.print("Enter Student ID : "); int studentId = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Company ID : "); int companyId = sc.nextInt(); sc.nextLine();
        as.applyToCompany(studentId, companyId);
    }

    private static void handleEligibleStudents(Scanner sc, CompanyService cs) {
        cs.viewAllCompanies();
        System.out.println("\n─── Check Eligible Students ───");
        System.out.print("Enter Company Name exactly : ");
        String companyName = sc.nextLine();
        cs.checkEligibleStudents(companyName);
    }

    private static void handleUpdateStatus(Scanner sc, ApplicationService as) {
        as.viewAllApplications();
        System.out.println("\n─── Update Application Status ───");
        System.out.print("Enter Application ID : "); int appId = sc.nextInt(); sc.nextLine();
        System.out.println("Status options: Pending / Selected / Rejected / Under Review");
        System.out.print("Enter New Status     : "); String status = sc.nextLine();
        as.updateApplicationStatus(appId, status);
    }
}
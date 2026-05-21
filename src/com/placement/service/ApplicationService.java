package com.placement.service;

import com.placement.db.DatabaseConnection;
import java.sql.*;

public class ApplicationService {

    public void applyToCompany(int studentId, int companyId) {

        // First check if student and company exist
        if (!studentExists(studentId)) {
            System.out.println(" No student found with ID: " + studentId);
            return;
        }

        if (!companyExists(companyId)) {
            System.out.println(" No company found with ID: " + companyId);
            return;
        }

        // Check if already applied
        if (alreadyApplied(studentId, companyId)) {
            System.out.println(" Student already applied to this company!");
            return;
        }

        String sql = "INSERT INTO applications (student_id, company_id, status) VALUES (?, ?, 'Pending')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, companyId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Application submitted successfully! Status: Pending");
            }

        } catch (SQLException e) {
            System.out.println(" Error applying: " + e.getMessage());
        }
    }


    public void viewAllApplications() {

        // JOIN query combining all 3 tables
        String sql = "SELECT a.application_id, s.name AS student_name, " +
                "s.cgpa, c.company_name, c.role, c.ctc, a.status " +
                "FROM applications a " +
                "JOIN students s  ON a.student_id  = s.student_id " +
                "JOIN companies c ON a.company_id  = c.company_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            boolean hasData = false;
            System.out.println("\n========== ALL APPLICATIONS ==========");

            while (rs.next()) {
                hasData = true;
                System.out.println("--------------------------------------");
                System.out.println("Application ID : " + rs.getInt("application_id"));
                System.out.println("Student Name   : " + rs.getString("student_name"));
                System.out.println("CGPA           : " + rs.getDouble("cgpa"));
                System.out.println("Company        : " + rs.getString("company_name"));
                System.out.println("Role           : " + rs.getString("role"));
                System.out.println("CTC            : " + rs.getDouble("ctc") + " LPA");
                System.out.println("Status         : " + rs.getString("status"));
            }

            System.out.println("--------------------------------------");

            if (!hasData) {
                System.out.println("  No applications found.");
            }

        } catch (SQLException e) {
            System.out.println(" Error fetching applications: " + e.getMessage());
        }
    }


    public void updateApplicationStatus(int applicationId, String newStatus) {

        String sql = "UPDATE applications SET status=? WHERE application_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, applicationId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Application status updated to: " + newStatus);
            } else {
                System.out.println(" No application found with ID: " + applicationId);
            }

        } catch (SQLException e) {
            System.out.println(" Error updating status: " + e.getMessage());
        }
    }


    private boolean studentExists(int studentId) {
        String sql = "SELECT student_id FROM students WHERE student_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // returns true if a row was found
        } catch (SQLException e) {
            return false;
        }
    }

    // Check if company exists in database
    private boolean companyExists(int companyId) {
        String sql = "SELECT company_id FROM companies WHERE company_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, companyId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    // Check if student already applied to this company
    private boolean alreadyApplied(int studentId, int companyId) {
        String sql = "SELECT application_id FROM applications WHERE student_id=? AND company_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, companyId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}
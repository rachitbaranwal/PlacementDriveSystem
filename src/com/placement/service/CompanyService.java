package com.placement.service;

import com.placement.db.DatabaseConnection;
import com.placement.util.Validator;
import java.sql.*;

public class CompanyService {

    public void addCompany(String name, double minCgpa, String role, double ctc) {

        // Validate inputs first
        if (!Validator.isValidName(name))    return;
        if (!Validator.isValidCgpa(minCgpa)) return;
        if (!Validator.isValidCtc(ctc))      return;

        if (role == null || role.trim().isEmpty()) {
            System.out.println(" Role cannot be empty!");
            return;
        }

        String sql = "INSERT INTO companies (company_name, min_cgpa, role, ctc) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name.trim());
            stmt.setDouble(2, minCgpa);
            stmt.setString(3, role.trim());
            stmt.setDouble(4, ctc);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Company added successfully!");
            }

        } catch (SQLException e) {
            System.out.println(" Error adding company: " + e.getMessage());
        }
    }


    public void viewAllCompanies() {

        String sql = "SELECT * FROM companies";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            boolean hasData = false;
            System.out.println("\n========== ALL COMPANIES ==========");

            while (rs.next()) {
                hasData = true;
                System.out.println("-----------------------------");
                System.out.println("ID       : " + rs.getInt("company_id"));
                System.out.println("Name     : " + rs.getString("company_name"));
                System.out.println("Min CGPA : " + rs.getDouble("min_cgpa"));
                System.out.println("Role     : " + rs.getString("role"));
                System.out.println("CTC      : " + rs.getDouble("ctc") + " LPA");
            }

            System.out.println("-----------------------------");

            if (!hasData) {
                System.out.println("  No companies found in database.");
            }

        } catch (SQLException e) {
            System.out.println(" Error fetching companies: " + e.getMessage());
        }
    }



    public void checkEligibleStudents(String companyName) {

        // JOIN query — finds students whose CGPA meets company's minimum
        String sql = "SELECT s.student_id, s.name, s.cgpa, s.branch, " +
                "c.company_name, c.min_cgpa " +
                "FROM students s " +
                "JOIN companies c ON s.cgpa >= c.min_cgpa " +
                "WHERE c.company_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, companyName);
            ResultSet rs = stmt.executeQuery();

            boolean hasData = false;
            System.out.println("\n===== ELIGIBLE STUDENTS FOR " + companyName + " =====");

            while (rs.next()) {
                hasData = true;
                System.out.println("-----------------------------");
                System.out.println("Student ID : " + rs.getInt("student_id"));
                System.out.println("Name       : " + rs.getString("name"));
                System.out.println("CGPA       : " + rs.getDouble("cgpa"));
                System.out.println("Branch     : " + rs.getString("branch"));
                System.out.println("Min CGPA   : " + rs.getDouble("min_cgpa"));
            }

            System.out.println("-----------------------------");

            if (!hasData) {
                System.out.println("  No eligible students found for " + companyName);
            }

        } catch (SQLException e) {
            System.out.println(" Error checking eligibility: " + e.getMessage());
        }
    }
}
package com.placement.service;

import com.placement.db.DatabaseConnection;
import com.placement.model.Student;
import com.placement.util.Validator;
import java.sql.*;
import java.util.ArrayList;

public class StudentService {


    public void addStudent(String name, String email, double cgpa, String branch) {

        // Validate all inputs BEFORE touching the database
        if (!Validator.isValidName(name))   return;
        if (!Validator.isValidEmail(email)) return;
        if (!Validator.isValidCgpa(cgpa))   return;

        if (branch == null || branch.trim().isEmpty()) {
            System.out.println(" Branch cannot be empty!");
            return;
        }

        String sql = "INSERT INTO students (name, email, cgpa, branch) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name.trim());
            stmt.setString(2, email.trim());
            stmt.setDouble(3, cgpa);
            stmt.setString(4, branch.trim());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student added successfully!");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println(" Email already exists! Use a different email.");
            } else {
                System.out.println(" Error adding student: " + e.getMessage());
            }
        }
    }

    public void viewAllStudents() {

        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // ResultSet is like a table of results
            // rs.next() moves to the next row — returns false when no more rows
            boolean hasData = false;

            System.out.println("\n========== ALL STUDENTS ==========");

            while (rs.next()) {
                hasData = true;

                // Read each column by its name
                int    id     = rs.getInt("student_id");
                String name   = rs.getString("name");
                String email  = rs.getString("email");
                double cgpa   = rs.getDouble("cgpa");
                String branch = rs.getString("branch");

                // Print neatly
                System.out.println("-----------------------------");
                System.out.println("ID     : " + id);
                System.out.println("Name   : " + name);
                System.out.println("Email  : " + email);
                System.out.println("CGPA   : " + cgpa);
                System.out.println("Branch : " + branch);
            }

            System.out.println("-----------------------------");

            if (!hasData) {
                System.out.println("⚠  No students found in database.");
            }

        } catch (SQLException e) {
            System.out.println(" Error fetching students: " + e.getMessage());
        }
    }

    public void updateStudent(int studentId, String newName, String newEmail,
                              double newCgpa, String newBranch) {

        String sql = "UPDATE students SET name=?, email=?, cgpa=?, branch=? WHERE student_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setString(2, newEmail);
            stmt.setDouble(3, newCgpa);
            stmt.setString(4, newBranch);
            stmt.setInt(5, studentId);  // The WHERE condition

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("  Student updated successfully!");
            } else {
                System.out.println(" No student found with ID: " + studentId);
            }

        } catch (SQLException e) {
            System.out.println(" Error updating student: " + e.getMessage());
        }
    }

    public void deleteStudent(int studentId) {

        String sql = "DELETE FROM students WHERE student_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("  Student deleted successfully!");
            } else {
                System.out.println(" No student found with ID: " + studentId);
            }

        } catch (SQLException e) {
            System.out.println(" Error deleting student: " + e.getMessage());
        }
    }
}
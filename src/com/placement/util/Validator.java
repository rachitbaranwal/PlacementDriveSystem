package com.placement.util;

public class Validator {
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println(" Name cannot be empty!");
            return false;
        }
        if (name.trim().length() < 2) {
            System.out.println(" Name must be at least 2 characters!");
            return false;
        }
        return true;
    }


    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println(" Email cannot be empty!");
            return false;
        }
        if (!email.contains("@") || !email.contains(".")) {
            System.out.println(" Invalid email format! Must contain @ and .");
            return false;
        }
        return true;
    }

    public static boolean isValidCgpa(double cgpa) {
        if (cgpa < 0.0 || cgpa > 10.0) {
            System.out.println(" CGPA must be between 0.0 and 10.0!");
            return false;
        }
        return true;
    }

    public static boolean isValidCtc(double ctc) {
        if (ctc <= 0) {
            System.out.println(" CTC must be greater than 0!");
            return false;
        }
        return true;
    }


    public static boolean isValidStatus(String status) {
        String[] allowed = {"Pending", "Selected", "Rejected", "Under Review"};
        for (String s : allowed) {
            if (s.equalsIgnoreCase(status)) {
                return true;
            }
        }
        System.out.println(" Invalid status! Choose: Pending / Selected / Rejected / Under Review");
        return false;
    }
}
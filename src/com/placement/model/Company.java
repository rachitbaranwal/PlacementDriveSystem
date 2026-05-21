package com.placement.model;

public class Company {

    int    companyId;
    String companyName;
    double minCgpa;
    String role;
    double ctc;

    public Company(int companyId, String companyName,
                   double minCgpa, String role, double ctc) {
        this.companyId   = companyId;
        this.companyName = companyName;
        this.minCgpa     = minCgpa;
        this.role        = role;
        this.ctc         = ctc;
    }

    public void displayCompany() {
        System.out.println("-----------------------------");
        System.out.println("ID       : " + companyId);
        System.out.println("Name     : " + companyName);
        System.out.println("Min CGPA : " + minCgpa);
        System.out.println("Role     : " + role);
        System.out.println("CTC      : " + ctc + " LPA");
        System.out.println("-----------------------------");
    }
}
package com.placement.model;

public class Application {

    int    applicationId;
    int    studentId;
    int    companyId;
    String status;

    public Application(int applicationId, int studentId,
                       int companyId, String status) {
        this.applicationId = applicationId;
        this.studentId     = studentId;
        this.companyId     = companyId;
        this.status        = status;
    }

    public void displayApplication() {
        System.out.println("-----------------------------");
        System.out.println("Application ID : " + applicationId);
        System.out.println("Student ID     : " + studentId);
        System.out.println("Company ID     : " + companyId);
        System.out.println("Status         : " + status);
        System.out.println("-----------------------------");
    }
}
package com.weshoponline.model;

public class Employee {
    private String name;
    private String empId;
    private String department;
    private double salary;

    // Constructor
    public Employee(String name, String empId, String department, double salary) {
        this.name = name;
        this.empId = empId;
        this.department = department;
        this.salary = salary;
    }

    // Default constructor
    public Employee() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmpId() {
        return empId;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Optionally, override the toString method for easy printing of employee information
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", empId=" + empId +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
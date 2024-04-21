package com.weshoponline.service;

import com.weshoponline.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(String empId);
}

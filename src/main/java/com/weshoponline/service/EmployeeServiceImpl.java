package com.weshoponline.service;

import com.weshoponline.eHandler.EmployeeNotFoundException;
import com.weshoponline.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> getAllEmployee() {
        return employees;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee getEmployeeById(String empId) {
        return employees
                .stream()
                .filter(
                        employee -> employee.getEmployeeId().equals(empId)
                ).findFirst()
                .orElseThrow( () -> new EmployeeNotFoundException("Employee not found with" +empId));
        //.orElseThrow( () -> new RuntimeException("Employee not found with" +empId));
    }
}
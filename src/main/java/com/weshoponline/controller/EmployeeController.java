package com.weshoponline.controller;

import com.weshoponline.model.Employee;
import com.weshoponline.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
//    public EmployeeController(EmployeeService employeeService) {}

    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        System.out.println("Inside addEmployee");
        employeeService.saveEmployee(employee);
        return employee;
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeByID(@PathVariable String empId){
        return employeeService.getEmployeeById(empId);
    }
}

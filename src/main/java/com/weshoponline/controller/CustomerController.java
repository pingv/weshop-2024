package com.weshoponline.controller;

import com.weshoponline.model.Customer;
import com.weshoponline.model.Employee;
import com.weshoponline.service.CustomerService;
import com.weshoponline.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{custLN}")
    public List<Customer> getCustomerByLN(@PathVariable("custLN") String custLN) {
        System.err.println("getCustomerByLN --- controller --- BEEP BEEP BEEP --- " + custLN);
        return customerService.getCustomersByLN(custLN);
    }

    @GetMapping("/startswith/{subStr}")
    public List<Customer> getCustomersByFirstNameContaining(@PathVariable("subStr") String subStr) {
        System.err.println("getCustomerByLN --- controller --- BEEP BEEP BEEP --- " + subStr);
        return customerService.getCustomersByFirstNameStartingWith(subStr);
    }

}


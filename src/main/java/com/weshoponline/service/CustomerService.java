package com.weshoponline.service;

import com.weshoponline.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    List<Customer> getCustomersByLastName(String lastName);
    List<Customer> getCustomersByLN(String lastName);
    void saveCustomer(Customer customer);
    List<Customer> getCustomersByFirstNameContaining(String subStr);
    List<Customer> getCustomersByFirstNameStartingWith(String subStr);
    List<Customer> getCustomersSorted();
}
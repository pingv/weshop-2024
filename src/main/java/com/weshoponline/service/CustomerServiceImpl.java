package com.weshoponline.service;

import com.weshoponline.model.Customer;
import com.weshoponline.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @Override
    public List<Customer> getCustomersByLN(String lastName) {
        System.err.println("--- getCustomersByLN --- " +lastName);
        return customerRepository.findByLN(lastName);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomersSorted() {
        return jdbcTemplate.query("CALL get_customers_sorted()", new CustomerRowMapper());
    }

    private static class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getLong("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setEmail(rs.getString("email"));
            return customer;
        }
    }

    public List<Customer> getDistinctCustomersByLastAndFirstName(String lastName, String firstName) {
        return customerRepository.findDistinctByLastNameAndFirstName(lastName, firstName);
    }

    public List<Customer> getCustomersByFirstNameStartingWith(String prefix) {
        return customerRepository.findByFirstNameStartingWithIgnoreCase(prefix);
    }

    public List<Customer> getCustomersByFirstNameEndingWith(String suffix) {
        return customerRepository.findByFirstNameEndingWith(suffix);
    }

    public List<Customer> getCustomersByFirstNameContaining(String infix) {
        return customerRepository.findByFirstNameContaining(infix);
    }

    public List<Customer> getCustomersByLastNameNotContaining(String infix) {
        return customerRepository.findByLastNameNotContaining(infix);
    }
}
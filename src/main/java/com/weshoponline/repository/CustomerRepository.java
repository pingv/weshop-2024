package com.weshoponline.repository;

import java.util.List;

import com.weshoponline.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);

    @Query("SELECT c FROM Customer c WHERE c.lastName = :lastName")
    List<Customer> findByLN(@Param("lastName") String lastName);

    List<Customer> findDistinctByLastNameAndFirstName(String lastName, String firstName);

    List<Customer> findByFirstNameStartingWith(String prefix);

    List<Customer> findByFirstNameStartingWithIgnoreCase(String prefix);

    List<Customer> findByFirstNameEndingWith(String suffix);

    List<Customer> findByFirstNameContaining(String infix);

    List<Customer> findByLastNameNotContaining(String infix);

//    List<Customer> IsStartingWith(String subStr);
}

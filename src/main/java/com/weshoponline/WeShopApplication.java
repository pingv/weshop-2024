package com.weshoponline;

import com.weshoponline.model.Customer;
import com.weshoponline.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.weshoponline.repository.CustomerRepository;


@SpringBootApplication
public class WeShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository, CustomerService customerService) {
		return (args) -> {
			// save a few customers
			customerService.saveCustomer(new Customer("Jack", "Bauer"));
			customerService.saveCustomer(new Customer("Chloe", "O'Brian"));
			customerService.saveCustomer(new Customer("Kim", "Bauer"));
			customerService.saveCustomer(new Customer("David", "Palmer"));
			customerService.saveCustomer(new Customer("Michelle", "Dessler"));
			customerService.saveCustomer(new Customer("vishnu", "parandhaman"));

			// fetch all customers
			System.out.println("Customers found with getAllCustomers():");
			System.out.println("---------------------------------------");
			customerService.getAllCustomers().forEach(customer -> {
				System.out.println(customer.toString());
			});
			System.out.println("");

			// fetch an individual customer by ID
			Customer customer = customerService.getCustomerById(1L);
			System.out.println("Customer found with getCustomerById(1L):");
			System.out.println("----------------------------------------");
			System.out.println(customer.toString());
			System.out.println("");

			// fetch customers by last name
			System.out.println("Customers found with getCustomersByLastName('Bauer'): - using service");
			System.out.println("-----------------------------------------------------");
			customerService.getCustomersByLastName("Bauer").forEach(bauer -> {
				System.out.println(bauer.toString());
			});
			System.out.println("");

			// fetch customers by last name using the repository directly
			System.out.println("Customers found with findByLastName('Bauer'): - using repository");
			System.out.println("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				System.out.println(bauer.toString());
			});
			System.out.println("");

			//TODO: Remove this code
			System.out.println("--------------------------------------------");
			repository.findByFirstNameStartingWith("J").forEach(j -> {
				System.out.println(j.toString());
			});
			System.out.println("");
		};
	}
}
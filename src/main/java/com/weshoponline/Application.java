package com.weshoponline;

import java.util.Arrays;

import com.weshoponline.model.Customer;
import com.weshoponline.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
			System.out.println("Test auto compile");
			System.out.println("Test auto compile---2");
			System.out.println("Test auto compile---333");

		};
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			System.out.println("Customers found with findAll():");
			System.out.println("-------------------------------");
			repository.findAll().forEach(customer -> {
				System.out.println(customer.toString());
			});
			System.out.println("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			System.out.println("Customer found with findById(1L):");
			System.out.println("--------------------------------");
			System.out.println(customer.toString());
			System.out.println("");

			// fetch customers by last name
			System.out.println("Customer found with findByLastName('Bauer'):");
			System.out.println("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				System.out.println(bauer.toString());
			});
			System.out.println("");
		};
	}
}
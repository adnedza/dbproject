package com.agh.northwindproject;

import com.agh.northwindproject.Categories.CategoriesRepository;
import com.agh.northwindproject.Categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NorthwindProjectApplication implements CommandLineRunner {
    @Autowired
    private CustRepository custRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public static void main(String[] args) {

        SpringApplication.run(NorthwindProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        custRepository.deleteAll();
        categoriesRepository.deleteAll();

        // save a couple of customers
        custRepository.save(new Cust("Alice", "Smith"));
        custRepository.save(new Cust("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Cust cust : custRepository.findAll()) {
            System.out.println(cust);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(custRepository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Cust cust : custRepository.findByLastName("Smith")) {
            System.out.println(cust);
        }

        Category newCategory = new Category("laptopy", "https://picture");
        categoriesRepository.save(newCategory);

    }
}

package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.Customer;
import fi.experis.eyeTunes.dataAccess.models.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @GetMapping("/")
    public String index() {
        return "this is the index page";
    }

    @GetMapping("/customers/{customerId}")
    public Customer customerById(@PathVariable String customerId) {
        return customerRepository.getCustomerById(customerId);
    }

    @GetMapping("/customers")
    public ArrayList<Customer> allCustomers() {
        return customerRepository.getAllCustomers();
    }
}

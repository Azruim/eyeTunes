package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @GetMapping("/")
    public String index() {
        return "this is the index page";
    }

    @GetMapping("/customers/{customerId}")
    public String customerById(@PathVariable String customerId) {
        return customerRepository.getCustomerById(customerId).toString();
    }

    @GetMapping("/customers")
    public String allCustomers() {
        return customerRepository.getAllCustomers().toString();
    }
}

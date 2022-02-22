package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.Customer;
import fi.experis.eyeTunes.dataAccess.models.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @GetMapping("/api/customers/name/{name}")
    public Customer customerByName(@PathVariable String name) {
            return customerRepository.getCustomerByName(name);
    }

    @GetMapping("/api/customers/id/{customerId}")
    public Customer customerById(@PathVariable String customerId) {
        return customerRepository.getCustomerById(customerId);
    }

    @GetMapping("/api/customers/all")
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @GetMapping("/api/customers/")
    public ArrayList<Customer> getCustomersByLimitAndOffset(@RequestParam(value = "limit", required = false) String limit,
                                                            @RequestParam(value = "offset",required = false) String offSet) {
        return customerRepository.getNumberOfCustomers(limit, offSet);
    }

    @RequestMapping(
            value = "/api/customers/update/",
            method = RequestMethod.PATCH)
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerRepository.updateCustomer(customer);
    }

    @PostMapping("/api/customers/")
    public Customer createNewCustomer(@RequestBody Customer customer) {
        return customerRepository.createNewCustomer(customer);
    }
}

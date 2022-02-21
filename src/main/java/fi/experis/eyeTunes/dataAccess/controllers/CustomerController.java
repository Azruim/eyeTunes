package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.Customer;
import fi.experis.eyeTunes.dataAccess.models.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @GetMapping("/")
    public String index() {
        return "this is the index page";
    }

    @GetMapping("/api/customer")
    public Customer customerByParameter(@RequestParam(defaultValue = "1") String customerId,
                                        @RequestParam(required = false) String name) {
        if (name != null) {
            return customerRepository.getCustomerByName(name);
        }
        return customerRepository.getCustomerById(customerId);

    }

    @GetMapping("/api/customers")
    public ArrayList<Customer> allCustomers(@RequestParam(value = "limit", required = false) String limit,
                                            @RequestParam(value = "offset",required = false) String offSet) {
        if (limit != null || offSet != null) {
            return customerRepository.getNumberOfCustomers(limit, offSet);
        }
        return customerRepository.getAllCustomers();
    }
}

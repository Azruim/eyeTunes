package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    @GetMapping("/")
    public String index() {
        return "this is the index page";
    }

    @GetMapping("/customers")
    public String customers() {
        return customerRepository.getCustomerById("1").toString();
    }


}

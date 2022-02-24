package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.CustomerCountry;
import fi.experis.eyeTunes.dataAccess.models.CustomerCountryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CustomerCountryAPIController {
    CustomerCountryRepository customerCountryRepository = new CustomerCountryRepository();

    @GetMapping("/api/statistics/countries/customers/count")
    public ArrayList<CustomerCountry> getCustomerCountByCountry() {
        return customerCountryRepository.getCustomerCountByCountry();
    }
}

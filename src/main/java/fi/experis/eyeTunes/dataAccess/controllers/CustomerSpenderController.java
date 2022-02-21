package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.CustomerSpender;
import fi.experis.eyeTunes.dataAccess.models.CustomerSpenderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CustomerSpenderController {
    CustomerSpenderRepository customerSpenderRepository = new CustomerSpenderRepository();

    @GetMapping("/api/statistics/customers/top-spenders")
    public ArrayList<CustomerSpender> getTopCustomerSpenders() {
        return customerSpenderRepository.getTopCustomerSpenders();
    }
}

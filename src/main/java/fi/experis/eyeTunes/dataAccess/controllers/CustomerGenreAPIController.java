package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.CustomerGenre;
import fi.experis.eyeTunes.dataAccess.models.CustomerGenreRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CustomerGenreAPIController {
    CustomerGenreRepository customerGenreRepository = new CustomerGenreRepository();

    @GetMapping("/api/customers/{customerId}/popular/genre")
    public ArrayList<CustomerGenre> getPopularCustomerGenre(@PathVariable String customerId) {
        return customerGenreRepository.getPopularCustomerGenre(customerId);
    }
}

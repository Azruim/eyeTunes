package fi.experis.eyeTunes.dataAccess.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/")
    public String index() {
        return "this is the index page";
    }

    @GetMapping("/customers")
    public String customers() {
        return "this is the customers page";
    }


}

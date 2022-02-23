package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.Customer;
import fi.experis.eyeTunes.dataAccess.models.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class CustomerController {
    CustomerRepository cRep = new CustomerRepository();

    @RequestMapping("/")
    public String home(Model model) {
        ArrayList<Customer> customers = cRep.getAllCustomers();
        model.addAttribute("customers" , customers);
        return "home";
    }

}

package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    CustomerRepository cRep = new CustomerRepository();
    GenreRepository gRep = new GenreRepository();
    SongRepository sRep = new SongRepository();

    @RequestMapping("/")
    public String home(Model model) {
        ArrayList<Customer> customers = cRep.getAllCustomers();
        List<Genre> genres = gRep.getRandomGenres(5);
        List<Song> songs = sRep.getRandomSongs(5);
        model.addAttribute("customers" , customers);
        model.addAttribute("genres" , genres);
        model.addAttribute("songs" , songs);
        return "home";
    }

}

package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.Artist;
import fi.experis.eyeTunes.dataAccess.models.ArtistRepository;
import fi.experis.eyeTunes.dataAccess.models.Customer;
import fi.experis.eyeTunes.dataAccess.models.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CustomerController {
    CustomerRepository cRep = new CustomerRepository();
    ArtistRepository aRep = new ArtistRepository();

    @RequestMapping("/")
    public String home(Model model) {
        List<Artist> artists = aRep.getAllArtists();
        model.addAttribute("artists" , artists);
        return "home";
    }

}

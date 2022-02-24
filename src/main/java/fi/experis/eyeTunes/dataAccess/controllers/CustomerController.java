package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class CustomerController {
    ArtistRepository aRep = new ArtistRepository();
    GenreRepository gRep = new GenreRepository();
    SongRepository sRep = new SongRepository();
    SearchRepository srcRep = new SearchRepository();

    @RequestMapping("/")
    public String home(Model model) {
        List<Artist> artists = aRep.getRandomArtists(5);
        List<Genre> genres = gRep.getRandomGenres(5);
        List<Song> songs = sRep.getRandomSongs(5);
        model.addAttribute("artists" , artists);
        model.addAttribute("genres" , genres);
        model.addAttribute("songs" , songs);
        model.addAttribute("searchString", "");
        return "home";
    }

    @PostMapping("/results")
    public String results(@RequestParam String searchString, Model model) {
        List<SearchItem> searchItems = srcRep.searchSongsByString(searchString);
        model.addAttribute("songs", searchItems);
        System.out.println(model.getAttribute("songs"));
        return "results";
    }
}

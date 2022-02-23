package fi.experis.eyeTunes.dataAccess.controllers;

import fi.experis.eyeTunes.dataAccess.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CustomerController {
    ArtistRepository aRep = new ArtistRepository();
    GenreRepository gRep = new GenreRepository();
    SongRepository sRep = new SongRepository();
    SearchRepository srcRep = new SearchRepository();

    @RequestMapping("/")
    public String home(Model model) {
        String searchString = "";
        model.addAttribute("searchString", searchString);
        List<Artist> artists = aRep.getRandomArtists(5);
        List<Genre> genres = gRep.getRandomGenres(5);
        List<Song> songs = sRep.getRandomSongs(5);
        model.addAttribute("artists" , artists);
        model.addAttribute("genres" , genres);
        model.addAttribute("songs" , songs);
        return "home";
    }

    @PostMapping("/")
    public String results(@ModelAttribute String searchString) {
        List<SearchItem> searchItems = srcRep.searchSongsByString(searchString);
        System.out.println(searchItems);
        return "home";
    }
}

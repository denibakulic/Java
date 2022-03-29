package com.bakulic.CinemaTicketShop.controller;

import com.bakulic.CinemaTicketShop.model.Movie;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateMovieDTO;
import com.bakulic.CinemaTicketShop.model.dto.requests.RegisterUserAccountDTO;
import com.bakulic.CinemaTicketShop.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/movie")
@Controller
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @ModelAttribute("movie")
    public CreateOrUpdateMovieDTO registerUserAccountDTO(){return new CreateOrUpdateMovieDTO();}


    @GetMapping
    public String getMovieForm(Model model) {
        CreateOrUpdateMovieDTO newMovie = new CreateOrUpdateMovieDTO();
        model.addAttribute("movie", newMovie);
        return "createMovieForm";
    }

    @PostMapping
    public String saveMovie(@ModelAttribute("movie")  CreateOrUpdateMovieDTO createOrUpdateMovieDTO ) {
        movieService.createMovie(createOrUpdateMovieDTO);
        return "redirect:movie/all";
    }

    @GetMapping("/update/{id}")
    public String getUpdateMovieForm (Model model, @PathVariable("id") int id) {
        Movie movie = movieService.getMovieRepository().findById(id);
        model.addAttribute("movie", movie);
        return "updateMovieForm";
    }

    @PostMapping("update/{id}")
    public String saveUpdateMovie(@PathVariable("id") int id, @ModelAttribute("movie") CreateOrUpdateMovieDTO createOrUpdateMovieDTO) {
        movieService.updateMovie(id, createOrUpdateMovieDTO);
        return "redirect:movie/all";
    }

    @GetMapping("/all")//koristi se
    public String getMovieList(Model model) {
        List<Movie> list = movieService.getAllMovies();
        System.err.println("picture:"+list.get(0).getPicture());
        model.addAttribute("movies", list);
        return "movieList";
    }

    @GetMapping("delete/{id}")
    public String deleteMovieById( @PathVariable("id") int id) {
        movieService.deleteMovieById(id);
        return "redirect:/movie/all";
    }
}

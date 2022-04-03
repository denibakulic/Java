package com.bakulic.CinemaTicketShop.controller;

import com.bakulic.CinemaTicketShop.model.Hall;
import com.bakulic.CinemaTicketShop.model.Movie;
import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.Seat;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateProjectionDTO;
import com.bakulic.CinemaTicketShop.service.HallService;
import com.bakulic.CinemaTicketShop.service.MovieService;
import com.bakulic.CinemaTicketShop.service.ProjectionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping(value = "/projection")
public class ProjectionController {

    @Autowired
    private ProjectionService projectionService;

    @Autowired
    private HallService hallService;

    @Autowired
    private MovieService movieService;

    @ModelAttribute("projection")
    public CreateOrUpdateProjectionDTO createOrUpdateProjectionDTO(){return new CreateOrUpdateProjectionDTO();}

    @GetMapping
    public String getProjectionForm(Model model) {
        CreateOrUpdateProjectionDTO newProjection = new CreateOrUpdateProjectionDTO();
        List<Hall> hallList = hallService.getAllHalls();
        List<Movie> movieList = movieService.getAllMovies();
        model.addAttribute("projection", newProjection);
        model.addAttribute("halls", hallList);
        model.addAttribute("movies", movieList);
        return "/createProjectionForm";
    }

    @PostMapping
    public String saveProjection(@ModelAttribute("projection")  CreateOrUpdateProjectionDTO createOrUpdateProjectionDTO) {
        projectionService.createProjection(createOrUpdateProjectionDTO);
        return "redirect:projection/all";
    }

    @GetMapping("/update/{id}")
    public String getUpdateProjectionForm(Model model, @PathVariable("id") int id) {
        Projection projection = projectionService.getProjectionRepository().findById(id);
        model.addAttribute("projection", projection);
        return "updateProjectionForm";
    }

    @PostMapping("/update/{id}")
    public String updateProjection(@PathVariable("id") int id, @ModelAttribute("projection")  CreateOrUpdateProjectionDTO projection) {
        projectionService.updateProjection(id, createOrUpdateProjectionDTO());
        return "redirect:/projection/all";
    }

    @GetMapping("/all")
    public String getProjList(Model model) {
        List<Projection> list = projectionService.getAllProjections();
        model.addAttribute("projections", list);
        return "projectionList";
    }

   /* @GetMapping("/{id}")
    public String getProjById(Model model, @PathVariable("id") int id) {
        Projection proj = projectionService.getProjectionRepository().findById(id);
        model.addAttribute("projections", proj);
        return "projectionDetails";
    }
*/
    @GetMapping("/{name}")
    public String getProjByMovie(Model model, @PathVariable("name") String name) {
        Collection<Projection> list = projectionService.getProjectionsByMovie(name);
        model.addAttribute("projections", list);
        return "projectionDetails";
    }


    @GetMapping("/delete/{id}")
    public  String deleteProjById(@PathVariable ("id") int id){
        projectionService.getProjectionRepository().deleteById(id);
        return "redirect:/projection/all";
    }
}

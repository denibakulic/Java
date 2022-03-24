package com.bakulic.CinemaTicketShop.controller;

import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateProjectionDTO;
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

    @GetMapping
    public String getProjectionForm(Model model) {
        CreateOrUpdateProjectionDTO newProjection = new CreateOrUpdateProjectionDTO();
        model.addAttribute("projection", newProjection);
        return "/createProjectionForm";
    }

    @PostMapping
    public String saveProjection(@ModelAttribute("projection")  CreateOrUpdateProjectionDTO createOrUpdateProjectionDTO) {
        projectionService.createProjection(createOrUpdateProjectionDTO);
        return "projectionList";
    }

    @GetMapping("/update/{id}")
    public String getUpdateProjectionForm(Model model, @PathVariable("id") int id) {
        Projection projection = projectionService.getProjectionRepository().findById(id);
        model.addAttribute("projection", projection);
        return "updateProjectionForm";
    }

    @PostMapping("/update/{id}")
    public String updateProjection(@PathVariable("id") int id, @ModelAttribute("projection")  CreateOrUpdateProjectionDTO projection) {
        projectionService.updateProjection(id, projection);
        return "projectionList";
    }

    @GetMapping("/all")//koristi se
    public String getProjList(Model model) {
        List<Projection> list = projectionService.getAllProjections();
        model.addAttribute("projections", list);
        return "projectionList";
    }

    @GetMapping("/{id}")//koristi se
    public String getProjById(Model model, @PathVariable("id") int id) {
        Projection proj = projectionService.getProjectionRepository().findById(id);
        model.addAttribute("projection", proj);
        return "projectionDetails";
    }

    @GetMapping("/movie")//koristi se
    public String getProjByMovie(Model model, @PathVariable("name") String name) {
        Collection<Projection> list = projectionService.getProjectionsByMovie(name);
        model.addAttribute("movieProjections", list);
        return "projectionDetails";
    }


    @GetMapping("/time")
    public String getProjByTime(@PathVariable(value = "time") String time, Model model) {
        Collection<Projection> list = projectionService.getProjectionsByTime(time);
        model.addAttribute("projections", list);
        return "projectionDetails";
    }

    @DeleteMapping("/delete/{id}")
    public  String deleteProjById(@PathVariable ("id") int id){
        projectionService.deleteProjectionById(id);
        return "projectionList";
    }
}

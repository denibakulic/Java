package com.bakulic.onlineherbarium.controller;

import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateHerbariumDTO;
import com.bakulic.onlineherbarium.service.HerbariumService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping(value = "/herbarium")
public class HerbariumController {

    @Autowired
    private final HerbariumService herbariumService;

    @GetMapping
    public String getHerbariumForm(Model model){
        CreateOrUpdateHerbariumDTO newHerbarium = new CreateOrUpdateHerbariumDTO();
        model.addAttribute("herbarium", newHerbarium);
        return "createHerbariumForm";
    }

    @PostMapping
    public String createHerbarium(@ModelAttribute("herbarium")  CreateOrUpdateHerbariumDTO createHerbariumDTO){
        herbariumService.createHerbarium(createHerbariumDTO);
        return "redirect:herbarium/all";
    }

    @GetMapping("update/{id}")
    public String getUpdateHerbariumForm(Model model, @PathVariable("id") int id){
        Herbarium herbarium = herbariumService.getHerbariumRepository().findById(id);
        model.addAttribute("herbarium", herbarium);
        return "updateHerbariumForm";
    }

    @PostMapping("/update/{id}")
    public String UpdateHerbarium(@PathVariable ("id") int id, @ModelAttribute("hall") CreateOrUpdateHerbariumDTO updateHerbariumDTO){
        herbariumService.updateHerbarium(id,updateHerbariumDTO);
        return "redirect:/herbarium/all";
    }

    @GetMapping("/all")
    public String getHallList(Model model) {
        List<Herbarium> list = herbariumService.getAllHerbariums();
        model.addAttribute("herbarium", list);
        return "herbariumList";
    }

    @GetMapping("/delete/{id}")
    public  String deleteHerbariumById( @PathVariable ("id") int id){
        herbariumService.getHerbariumRepository().deleteById(id);
        return "redirect:/herbarium/all";
    }
}

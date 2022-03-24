package com.bakulic.CinemaTicketShop.controller;

import com.bakulic.CinemaTicketShop.model.Hall;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateHallDTO;
import com.bakulic.CinemaTicketShop.service.HallService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/hall")
public class HallController {

    @Autowired
    private final HallService hallService;

    @GetMapping
    public String getHallForm(Model model){
        CreateOrUpdateHallDTO newHall = new CreateOrUpdateHallDTO();
        model.addAttribute("hall", newHall);
        return "createHallForm";
    }

    @PostMapping
    public String createHall(@ModelAttribute("hall")  CreateOrUpdateHallDTO createHallDTO){
        hallService.createHall(createHallDTO);
        return "hallList";
    }

    @GetMapping("update/{id}")
    public String getUpdateHallForm(Model model, @PathVariable ("id") int id){
        Hall hall = hallService.getHallRepository().findById(id);
        model.addAttribute("hall", hall);
        return "updateHallForm";
    }

    @PostMapping("/update/{id}")
    public String saveUpdateHall(@PathVariable ("id") int id, @ModelAttribute("hall") CreateOrUpdateHallDTO createOrUpdateHallDTO){
        hallService.updateHall(id,createOrUpdateHallDTO);
        return "hallList";
    }

    @GetMapping("/all")
    public String getHallList(Model model) {
        List<Hall> list = hallService.getAllHalls();
        model.addAttribute("halls", list);
        return "hallList";
    }

    @DeleteMapping("/delete/{id}")
    public  String deleteHallById( @PathVariable ("id") int id){
        hallService.deleteHallById(id);
        return "hallList";
    }

}

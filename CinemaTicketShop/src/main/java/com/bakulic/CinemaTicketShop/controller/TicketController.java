package com.bakulic.CinemaTicketShop.controller;

import com.bakulic.CinemaTicketShop.model.Projection;
import com.bakulic.CinemaTicketShop.model.Ticket;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateTicketDTO;
import com.bakulic.CinemaTicketShop.service.ProjectionService;
import com.bakulic.CinemaTicketShop.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ProjectionService projectionService;

    @GetMapping("/{projectionId}")
    public String getTicketForm(Model model,  @PathVariable("projectionId") int projId) {
        CreateTicketDTO newTicket = new CreateTicketDTO();
        var proj = projectionService.getProjectionRepository().findById(projId);
        model.addAttribute("proj", proj);
        model.addAttribute("ticket", newTicket);
        return "/createTicketForm";
    }

    @PostMapping("/{projectionId}")
    public String saveTicket(@ModelAttribute("ticket") CreateTicketDTO createTicketDTO, @PathVariable("projectionId") Integer projId){
        ticketService.createTicket(createTicketDTO, projId);
        return  "final";
    }

    @GetMapping("/all")
    public String getTicketList(Model model) {
        List<Ticket> list = ticketService.getAllTickets();
        model.addAttribute("tickets", list);
        return "ticketList";
    }

    @GetMapping("/username/{username}")
    public String getUsersTickets(Model model, @PathVariable("username") String username){
        Collection<Ticket> list = ticketService.getTicketRepository().findByUsername(username);
        model.addAttribute("tickets", list);
        return "userTicketList";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket( @PathVariable ("id") int id){
        ticketService.getTicketRepository().deleteById(id);
        return  "redirect:/ticket/all";
    }
}

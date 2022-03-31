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

@AllArgsConstructor
@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ProjectionService projectionService;

    @GetMapping
    public String getTicketForm(Model model) {
        CreateTicketDTO newTicket = new CreateTicketDTO();
        model.addAttribute("ticket", newTicket);
        return "/createTicketForm";
    }

    @PostMapping
    public String saveTicket(@ModelAttribute("ticket") CreateTicketDTO createTicketDTO, Projection proj){
        ticketService.createTicket(createTicketDTO, proj);
        return  "redirect:ticket/all";
    }

    @GetMapping("/all")
    public String getTicketList(Model model) {
        List<Ticket> list = ticketService.getAllTickets();
        model.addAttribute("tickets", list);
        return "ticketList";
    }

    @GetMapping("/{username}")
    public String getUsersTickets(Model model, @PathVariable("username") String username){
        Collection<Ticket> list = ticketService.geTicketByUsername(username);
        model.addAttribute("tickets", list);
        return "userTicketList";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket( @RequestParam @PathVariable ("id") int id){
        ticketService.getTicketRepository().deleteById(id);
        return  "redirect:/ticket/all";
    }
}

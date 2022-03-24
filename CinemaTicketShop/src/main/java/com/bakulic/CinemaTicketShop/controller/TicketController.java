package com.bakulic.CinemaTicketShop.controller;

import com.bakulic.CinemaTicketShop.model.Ticket;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateTicketDTO;
import com.bakulic.CinemaTicketShop.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String getTicketForm(Model model) {
        CreateTicketDTO newTicket = new CreateTicketDTO();
        model.addAttribute("ticket", newTicket);
        return "/createTicketForm";
    }

    @PostMapping
    public String saveTicket(@ModelAttribute("ticket") CreateTicketDTO createTicketDTO){
        CreateTicketDTO newTicket = new CreateTicketDTO();
        ticketService.createTicket(newTicket);
        return  "ticketList";
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

    @DeleteMapping("/delete/{id}")
    public String deleteTicket( @RequestParam @PathVariable ("id") int id){
        ticketService.deleteTicketById(id);
        return  "ticketList";
    }
}

package com.bakulic.CinemaTicketShop.service;

import com.bakulic.CinemaTicketShop.model.*;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateTicketDTO;
import com.bakulic.CinemaTicketShop.repository.ProjectionRepository;
import com.bakulic.CinemaTicketShop.repository.TicketRepository;
import com.bakulic.CinemaTicketShop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakulic.CinemaTicketShop.exceptions.*;

import java.util.*;

@AllArgsConstructor
@Service
public class TicketService extends ProjectionService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketRepository getTicketRepository(){
        return ticketRepository;
    }

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserRepository getUserRepository(){
        return userRepository;
    }

    @Autowired
    private final ProjectionRepository projectionRepository;

    @Autowired
    public ProjectionRepository getProjectionRepository(){
        return projectionRepository;
    }


    /** ticket create**/
    public Ticket createTicket(CreateTicketDTO creteTicketDTO, Projection proj) {
        if (creteTicketDTO == null) {
            throw new InvalidDataException("Ticket cannot be null");
        }
        var ticket = new Ticket();
        var user = userRepository.findByUsername(creteTicketDTO.getUsername());
        ticket.setUser(user);

        ticket.setProjection(proj);

        Integer seatNumber = Integer.valueOf(creteTicketDTO.getSeatNumber());
        ticket.setSeatNumber(seatNumber);


        var hall = proj.getHall();
        var numberOfSeats = hall.getNumberOfSeats();

        var list = proj.getSeatList();
        list.remove(seatNumber);

        proj.setSeatList(list);
        var ticketCreated = ticketRepository.save(ticket);
        log.info(String.format("Ticket %s has been created.", ticket.getTicketId()));
        return ticketCreated;

    }

    /**list of all halls*/
    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    /**get tickets of a user*/
    public Collection<Ticket> geTicketByUsername(String username){
        if(username == null){
            throw  new InvalidDataException("Username cannot be null");
        }
        return ticketRepository.findByUsername(username);
    }

}

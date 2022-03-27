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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.IntStream;

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
/*
    public Ticket getTicketById (int id) {
        Optional<Ticket> ticketOpt = Optional.of(ticketRepository.getById(id));
        if (ticketOpt.isPresent()) {
            return ticketOpt.get();
        }
        throw new ObjectNotFoundException(String.format("User not found for Id = %s", id));
    }
*/
    /** ticket create**/
    public Ticket createTicket(CreateTicketDTO creteTicketDTO, List<Integer> seatList) {
        if (creteTicketDTO == null) {
            throw new InvalidDataException("Ticket cannot be null");
        }
        Ticket ticket = new Ticket();
        ticket.setStatus("sold");
        User user = userRepository.findByUsername(creteTicketDTO.getUsername());
        ticket.setUser(user);

        Projection projection = projectionRepository.findByMovie_Name(creteTicketDTO.getMovieName());
        ticket.setProjection(projection);

        Integer seatNumber = Integer.valueOf(creteTicketDTO.getSeatNumber());
        ticket.setSeatNumber(seatNumber);


        var hall = projection.getHall();
        Integer numberOfSeats = hall.getNumberOfSeats();

        List<Integer> list = projection.getSeatList();
        IntStream.range(1, numberOfSeats)
                .forEach(index -> {
                    if (index == seatNumber){
                        list.remove(index);
                    }
                });

        projection.setSeatList(list);
        Ticket ticketCreated = ticketRepository.save(ticket);
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


    /**delete ticket*/
    public void deleteTicketById(int id){

        Ticket ticket = ticketRepository.findById(id);

        ticketRepository.deleteById(id);
        log.info(String.format("Ticket %s has been deleted.", id));
    }

}

package com.bakulic.CinemaTicketShop.service;

import com.bakulic.CinemaTicketShop.model.*;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateTicketDTO;
import com.bakulic.CinemaTicketShop.repository.TicketRepository;
import com.bakulic.CinemaTicketShop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bakulic.CinemaTicketShop.exceptions.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Ticket getTicketById (int id) {
        Optional<Ticket> ticketOpt = Optional.of(ticketRepository.getById(id));
        if (ticketOpt.isPresent()) {
            return ticketOpt.get();
        }
        throw new ObjectNotFoundException(String.format("User not found for Id = %s", id));
    }

    /** ticket create**/
    public Ticket createTicket(CreateTicketDTO creteTicketDTO) {
        if (creteTicketDTO == null) {
            throw new InvalidDataException("Ticket cannot be null");
        }
        Ticket ticket = new Ticket();
        ticket.setStatus(creteTicketDTO.getStatus());
        User user = userRepository.findByUsername(creteTicketDTO.getUsername());
        ticket.setUser(user);

        Projection projection = ticket.getProjection();
        if(projection == null){
            projection = new Projection();
        }
        Integer num = ticket.getSeatNumber();
        List<Seat> list = projection.getSeatList();
        for(int i = 0; i<list.size(); i++){ //stream
            Seat seat = new Seat();
            if(seat.getSeatNumber().equals(num) && seat.getStatus() =="empty" && num > list.size()){
                seat.setStatus(ticket.getStatus());
            }
            else throw new InvalidDataException("The seat you selected i not available");

        }

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

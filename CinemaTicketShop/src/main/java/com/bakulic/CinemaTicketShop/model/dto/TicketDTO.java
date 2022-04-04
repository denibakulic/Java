package com.bakulic.CinemaTicketShop.model.dto;

import com.bakulic.CinemaTicketShop.model.Ticket;
import lombok.Data;

import java.io.Serializable;


@Data
public class TicketDTO implements Serializable {
    private int id;
    private UserDTO userDTO;
    private Integer seatNumber;

    private ProjectionDTO projectionDTO;


    public TicketDTO(Ticket ticket){
        if (ticket != null){
            this.id = ticket.getTicketId();
            if(ticket.getProjection() != null){
                this.projectionDTO = new ProjectionDTO(ticket.getProjection());
            }
            if (ticket.getUser() != null){
                this.userDTO = new UserDTO(ticket.getUser());
            }

        }

    }

}


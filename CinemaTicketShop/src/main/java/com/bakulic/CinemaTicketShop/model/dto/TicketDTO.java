package com.bakulic.CinemaTicketShop.model.dto;

import com.bakulic.CinemaTicketShop.model.Ticket;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class TicketDTO implements Serializable {
    private int id;
    private  String status;
    private UserDTO userDTO;
    private Integer seatNumber;

    private ProjectionDTO projectionDTO;


    public TicketDTO(Ticket ticket){
        if (ticket != null){
            this.id = ticket.getTicketId();
            this.status = ticket.getStatus();
            if(ticket.getProjection() != null){
                this.projectionDTO = new ProjectionDTO(ticket.getProjection());
            }
            if (ticket.getUser() != null){
                this.userDTO = new UserDTO(ticket.getUser());
            }

        }

    }

}


package com.bakulic.CinemaTicketShop.model.mappers;

import com.bakulic.CinemaTicketShop.model.Ticket;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateTicketDTO;

public interface TicketMapper {

    CreateTicketDTO ticketToCreateTicketDTO (Ticket ticket);
}

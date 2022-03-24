package com.bakulic.CinemaTicketShop.model.dto;

import com.bakulic.CinemaTicketShop.model.Seat;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class SeatDTO implements Serializable {
    private int id;
    private Integer seatNumber;
    private String status;
    private ProjectionDTO projectionDTO;


    public  SeatDTO(Seat seat){
        if(seat != null){
            this.seatNumber = seat.getSeatNumber();
            this.status = seat.getStatus();
        }
        if (seat.getProjection() != null){
            this.projectionDTO = new ProjectionDTO(seat.getProjection());
        }
    }
}

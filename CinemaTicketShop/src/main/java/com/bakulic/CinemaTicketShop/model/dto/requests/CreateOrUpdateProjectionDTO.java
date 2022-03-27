package com.bakulic.CinemaTicketShop.model.dto.requests;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrUpdateProjectionDTO implements Serializable {
    private String date;
    private String startTime;
    private List<Integer> seatList;

    //aditional info
    private String name;
    private String movieName;

}


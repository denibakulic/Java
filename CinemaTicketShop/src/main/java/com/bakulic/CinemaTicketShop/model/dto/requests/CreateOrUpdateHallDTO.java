package com.bakulic.CinemaTicketShop.model.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrUpdateHallDTO implements Serializable {

    private String name;
    private Integer numberOfSeats;
    private String description;




}

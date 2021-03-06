package com.bakulic.CinemaTicketShop.model;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int seatId;

    @Column(name = "seatNumber")
    private Integer seatNumber;


    @ManyToOne
    @JoinColumn(name = "projectionId")
    private Projection projection;


}
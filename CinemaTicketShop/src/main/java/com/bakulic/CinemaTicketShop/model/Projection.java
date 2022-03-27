package com.bakulic.CinemaTicketShop.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projections")
public class Projection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int projectionId;

    @Column(name = "date")
    private String date;

    @Column(name = "startTime")
    private String startTime;

    @ManyToOne
    @JoinColumn(name = "idHall")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "idMovie")
    private Movie movie;

    @Column(name = "seatList")
    @ElementCollection
    private List<Integer> seatList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projection", cascade = CascadeType.ALL)
    private List<Ticket> ticketList;

}

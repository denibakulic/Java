package com.bakulic.CinemaTicketShop.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name ="username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "userRole")
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> ticketList;


}

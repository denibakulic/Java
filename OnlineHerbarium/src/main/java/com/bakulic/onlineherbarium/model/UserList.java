package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "userList")
@Data
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int listId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToMany(mappedBy = "lists")
    List<Plant> plants;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userList", cascade = CascadeType.ALL)
    private List<Image> imageList;

}

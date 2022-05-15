package com.bakulic.onlineherbarium.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "lists")
@Data
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int listId;

    @Column(name = "titles")
    private String title;

    @ManyToOne
    @JoinColumn(name = "isUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idPlant")
    private Plant plant;

    @Column(name = "notes")
    private String note;


}

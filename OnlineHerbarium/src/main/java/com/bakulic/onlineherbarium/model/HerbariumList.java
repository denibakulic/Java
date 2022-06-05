package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "herbariums_lists")
@Data
public class HerbariumList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int herbariumId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "pictures") //check what type of data to use
    private String picture;

    @ManyToOne
    @JoinColumn(name = "idPlant")
    private Plant plant;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @Column(name = "date")
    private String date;
}

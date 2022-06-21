package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int imageId;

    @Column(name = "name")
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}

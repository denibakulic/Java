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

    @Column(name="location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "idUserList")
    private UserList userList;

}

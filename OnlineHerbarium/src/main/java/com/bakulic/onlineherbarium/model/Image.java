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

    @Lob
    byte[] content;

    @ManyToOne
    @JoinColumn(name = "idUserList")
    private UserList userList;
}

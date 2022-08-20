package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int roleId;

    @Column(name = "name")
    private String name;



    public Role() {
        super();

    }
    public Role(String name) {
        super();
        this.name = name;
    }
}

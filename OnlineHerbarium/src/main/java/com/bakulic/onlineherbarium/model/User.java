package com.bakulic.onlineherbarium.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "userId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "roleId", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserList> userLists;

    public User() {

    }

    public User(String fullname, String username, String password, Collection<Role> roles, Boolean role) {
        super();
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}

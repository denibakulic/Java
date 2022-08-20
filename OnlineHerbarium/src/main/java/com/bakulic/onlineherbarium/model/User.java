package com.bakulic.onlineherbarium.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
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

    public User(String fullname, String email, String password, Collection<Role> roles, Boolean role) {
        super();
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}

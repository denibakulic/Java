package com.bakulic.CinemaTicketShop.repository;

import com.bakulic.CinemaTicketShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(int id);

    User save(User user);

}

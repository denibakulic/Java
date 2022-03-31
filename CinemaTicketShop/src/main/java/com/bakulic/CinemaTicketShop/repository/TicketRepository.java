package com.bakulic.CinemaTicketShop.repository;

import com.bakulic.CinemaTicketShop.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    /** all tickets of a customer*/
    @Query("FROM Ticket  t WHERE t.user.username = :username")
    Collection<Ticket> findByUsername(String username);

    Ticket findById(int id);

}

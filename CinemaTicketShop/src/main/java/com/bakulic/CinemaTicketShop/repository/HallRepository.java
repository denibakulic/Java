package com.bakulic.CinemaTicketShop.repository;

import com.bakulic.CinemaTicketShop.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {

    Hall findByName(String name);

    Hall findById(int id);
}
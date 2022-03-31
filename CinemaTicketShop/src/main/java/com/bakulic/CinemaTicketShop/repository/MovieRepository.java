package com.bakulic.CinemaTicketShop.repository;

import com.bakulic.CinemaTicketShop.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie getByName(String name);

    Movie findById(int id);
}

package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Herbarium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface HerbariumRepository extends JpaRepository<Herbarium, Integer> {

    Herbarium findById(int id);

   Herbarium findByTitle(String name);



}

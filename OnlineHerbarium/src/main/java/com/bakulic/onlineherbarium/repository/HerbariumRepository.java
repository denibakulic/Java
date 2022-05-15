package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Herbarium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Component
public interface HerbariumRepository extends JpaRepository<Herbarium, int> {

    Herbarium findByHerbariumId(int id);

    Herbarium findByTitle(String title);

}

package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Family;
import com.bakulic.onlineherbarium.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {
    Family findById(int id);

    Family findByName(String name);

    List<Family> findAllByOrderByNameAsc();
}

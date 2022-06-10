package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {
    Family findById(int id);

    Family findByName(String name);
}

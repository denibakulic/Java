package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.UserPlant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UserPlantRepository extends JpaRepository<UserPlant, int> {
}

package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByEmail(String currentUserEmail);
}

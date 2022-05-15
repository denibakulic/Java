package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UserRepository extends JpaRepository<User, int> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByUserId(int id);

    User save(User user);
}

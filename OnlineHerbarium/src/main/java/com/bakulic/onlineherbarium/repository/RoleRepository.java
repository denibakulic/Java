package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.Role;
import com.bakulic.onlineherbarium.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

    Role findById(int id);

    @Query("FROM User u WHERE u.role.roleId = :id")
    Collection<User> listOfAllUsersByRole(int id);
}

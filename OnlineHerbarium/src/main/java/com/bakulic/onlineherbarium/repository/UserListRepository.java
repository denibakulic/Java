package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UserListRepository extends JpaRepository<UserList, int> {
}

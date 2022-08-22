package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.Plant;
import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Integer> {

    UserList findById(int id);

    @Query("FROM UserList u WHERE u.user.userId = :id")
    Collection<UserList> listOfAllUserListByUser(int id);

}

package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Herbarium;
import com.bakulic.onlineherbarium.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query("FROM UserList ul WHERE ul.listId = :id")
    Collection<Image> listOfAllImagesByUserList(int id);

    @Query("FROM User u WHERE u.userId = :id")
    Collection<Image> listOfAllImagesByUser(int id);
}

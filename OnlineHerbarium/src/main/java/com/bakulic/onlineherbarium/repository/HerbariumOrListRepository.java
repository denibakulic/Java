package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.HerbariumOrList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface HerbariumOrListRepository extends JpaRepository<HerbariumOrList, Integer> {

    HerbariumOrList findById(int id);

    @Query("FROM Type t WHERE t.typeId = 1")
    Collection<HerbariumOrList> listOfAllHerbariums(int id);

    @Query("FROM Type t WHERE t.typeId = 2")
    Collection<HerbariumOrList> listOfAllLists(int id);
}

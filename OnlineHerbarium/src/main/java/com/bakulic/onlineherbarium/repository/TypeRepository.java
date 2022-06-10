package com.bakulic.onlineherbarium.repository;

import com.bakulic.onlineherbarium.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    Type findByTypeId(int id);



}

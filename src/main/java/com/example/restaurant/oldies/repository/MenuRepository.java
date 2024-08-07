package com.example.restaurant.oldies.repository;

import com.example.restaurant.oldies.entity.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
    Menu findByUniqueCode(String uniqueCode);
}
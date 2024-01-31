package com.example.postalitemsapplication.repository;

import com.example.postalitemsapplication.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}

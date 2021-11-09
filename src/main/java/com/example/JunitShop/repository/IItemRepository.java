package com.example.JunitShop.repository;

import com.example.JunitShop.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository  extends JpaRepository<Item, String> {
}

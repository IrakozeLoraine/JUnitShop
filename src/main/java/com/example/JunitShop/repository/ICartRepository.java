package com.example.JunitShop.repository;

import com.example.JunitShop.models.Cart;
import com.example.JunitShop.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, String> {
}

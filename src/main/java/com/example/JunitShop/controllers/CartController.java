package com.example.JunitShop.controllers;

import com.example.JunitShop.models.Cart;
import com.example.JunitShop.models.Item;
import com.example.JunitShop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/all-carts")
    public List<Cart> getAll(){
        return cartService.getAllCarts();
    }

    @PostMapping("/add-item")
    public Cart addCourse(@RequestBody Cart cart){
        return cartService.createCart(cart.getId(), cart.getCode());
    }

    @GetMapping("/{cartId}/items")
    public List<Item> retrieveItemsForCart(@PathVariable String cartId) throws Exception {
        return cartService.getAllItemsInCart(cartId);
    }

    @PostMapping("/{cartId}/newItem")
    public Cart addItemToCart(
            @PathVariable String cartId, @RequestBody Item newItem) throws Exception {

        return cartService.addItem(cartId, newItem);
    }
    @PostMapping("/{cartId}/newItems")
    public Cart addItemsToCart(
            @PathVariable String cartId, @RequestBody List<Item> newItems) throws Exception {

        return cartService.addItems(cartId, newItems);
    }

}
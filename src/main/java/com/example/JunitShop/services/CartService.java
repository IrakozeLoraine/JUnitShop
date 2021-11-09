package com.example.JunitShop.services;

import com.example.JunitShop.models.Cart;
import com.example.JunitShop.models.Item;
import com.example.JunitShop.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    ICartRepository cartRepository;

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart getCartById(String id) throws Exception {
        return cartRepository.findById(id)
                .orElseThrow(()->new Exception("Cart with id "+id+ " not found!"));
    }

    public List<Item> getAllItemsInCart(String cartId) throws Exception {
        Cart cart = getCartById(cartId);
        List<Item> items = cart.getItems();

        if(items.isEmpty()){
            return null;
        }
        return items;
    }

    public Cart createCart(String id, String code){
        Cart course = new Cart(id, code, 0, 0.0);
        return cartRepository.save(course);
    }



    public Cart addItems(String cartId, List<Item> items) throws Exception {
        Cart cart = getCartById(cartId);
        cart.setItems(items);
        cart.setTotalItems(items.size());

        Double totalPrices = 0.0;
        for (Item item: items){
            totalPrices += item.getPrice();
        }
        cart.setTotalPrice(totalPrices);


        cartRepository.save(cart);
        return cart;
    }

    public Cart addItem(String cartId, Item item) throws Exception {
        Cart cart = getCartById(cartId);

        cart.setItem(item);
        List<Item> items =  getAllItemsInCart(cart.getId());
        cart.setTotalItems(items.size()+1);
        cart.setTotalPrice(cart.getTotalPrice()+item.getPrice());

        cartRepository.save(cart);

        return cart;
    }

    public Cart removeItem(String cartId, String itemId) throws Exception {
        Cart cart = getCartById(cartId);
        List<Item> items =  getAllItemsInCart(cart.getId());

        for (Item item : cart.getItems()) {
            if (item.getId().equals(itemId)) {
                cart.getItems().remove(item);
                cart.setTotalItems(items.size()-1);
                cart.setTotalPrice(cart.getTotalPrice()-item.getPrice());
            }
        }
        cartRepository.save(cart);

        return cart;
    }


}

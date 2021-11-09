package com.example.JunitShop.services;

import com.example.JunitShop.models.Cart;
import com.example.JunitShop.models.Item;
import com.example.JunitShop.repository.ICartRepository;
import com.example.JunitShop.repository.IItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    ICartRepository cartRepository;

    @Mock
    IItemRepository itemRepository;

    @InjectMocks
    CartService cartService;


    @Test
    public void returnCarts(){
        when(cartRepository.findAll()).thenReturn(Arrays.asList(
                new Cart("1","C001"),
                new Cart("2","C002"),
                new Cart("3","C003")));
        assertEquals("C003",cartService.getAllCarts().get(2).getCode());
    }

    @Test
    public  void createCart(){
        when(cartRepository.save(ArgumentMatchers.any(Cart.class))).thenReturn(new Cart("4","C004"));
        assertEquals("C004",cartService.createCart("5","C005").getCode());
    }

    @Test
    public void addItemToCart() throws Exception {
        Cart cart = new Cart("6","C006", 0, 0.0);
        Item item = new Item("1","I001", "Nike", "Shoes",15000.0);
        cart.setItems(new ArrayList<>());

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.addItem(cart.getId(), item);

        List<Item> expected = cartService.getCartById(cart.getId()).getItems();
        assertThat(expected).isSameAs(cart.getItems());
        verify(cartRepository).save(cart);
    }

    @Test
    public void addItemsToCart() throws Exception {
        Cart cart = new Cart("6","C006");
        List<Item> items = new ArrayList<Item>(Arrays.asList(new Item("1","I001", "Nike", "Shoes",15000.0), new Item("2","I002", "White-Gown", "Dress",35000.0),
                new Item("3","I003", "Wing 55", "Necklace",12000.0)));
        cart.setItems(items);

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.addItems(cart.getId(), items);


        verify(cartRepository).save(cart);
        verify(cartRepository).findById(cart.getId());

        List<Item> expected = cartService.getCartById(cart.getId()).getItems();
        assertThat(expected).isSameAs(cart.getItems());
        verify(cartRepository).save(cart);
    }

    @Test
    public void retrieveCartItems() throws Exception {
        Cart cart = new Cart("6","C006");
        cart.setItems(new ArrayList<Item>(Arrays.asList(
                new Item("2","I002", "White-Gown", "Dress",35000.0),
                new Item("3","I003", "Wing 55", "Necklace",12000.0),
                new Item("4","I004", "Apple 54", "Apple Watch",75000.0))));

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.getAllItemsInCart(cart.getId());

        assertEquals("I003",cartService.getAllItemsInCart(cart.getId()).get(1).getCode());
    }

    @Test
    public void removeCartItem() throws Exception {
        Cart cart = new Cart("6","C006", 0, 0.0);
        cart.setItems(new ArrayList<Item>(Arrays.asList(
                new Item("4","I004", "White-Gown", "Dress",35000.0),
                new Item("5","I005", "Wing 55", "Necklace",12000.0),
                new Item("6","I006", "Apple 54", "Apple Watch",75000.0))));

        Item itemToDelete = new Item("6","I006", "Apple 54", "Apple Watch",75000.0);


        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        when(itemRepository.findById(itemToDelete.getId())).thenReturn(Optional.of(itemToDelete));

        cartService.removeItem(cart.getId(), itemToDelete.getId());

        verify(cartRepository).findById(cart.getId());
        verify(cartRepository).save(cart);
    }


}

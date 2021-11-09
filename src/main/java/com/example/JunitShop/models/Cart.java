package com.example.JunitShop.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cart {

    @Id
    private String id;
    private String code;
    private Integer totalItems;
    private Double totalPrice;

    @OneToMany
    private List<Item> items;

    public Cart() {
    }

    public Cart(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public Cart(String id, String code, Integer totalItems, Double totalPrice) {
        this.id = id;
        this.code = code;
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
    }

    public Cart(String id, String code, Integer totalItems, Double totalPrice, List<Item> items) {
        this.id = id;
        this.code = code;
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
        this.items = items;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setItem(Item item) {
        this.items.add(item);
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return String.format(
                "Cart [id=%s, code=%s, totalPrice=%s, totalItems=%s, items=%s]", id,
                code, totalPrice, totalItems, items);
    }
}

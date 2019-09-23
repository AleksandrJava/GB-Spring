package com.geekbrains.septembermarket.utils;

import com.geekbrains.septembermarket.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private HashMap<String, CartItem> cartItems;
    private int allCost;

    public HashMap<String, CartItem> getProducts() {
        return cartItems;
    }

    @PostConstruct
    public void init() {
        cartItems = new HashMap<>();
        allCost = 0;
    }

    public void addProduct(Product product) {
        if(cartItems.containsKey(product.getTitle())){
            CartItem item = cartItems.get(product.getTitle());
            item.setAmount(item.getAmount() + 1);
        } else {
            CartItem item = new CartItem(product.getId(), product.getTitle(),product.getPrice(), 1);
            cartItems.put(product.getTitle(), item);
        }
        allCost+=product.getPrice();
    }

    public void deleteProduct(Product product){
        CartItem cartItem = cartItems.get(product.getTitle());
        cartItem.setAmount(cartItem.getAmount() - 1);
        if(cartItem.getAmount() == 0){
            cartItems.remove(product.getTitle());
        }
        allCost-=product.getPrice();
    }

    public int getAllCost() {
        return allCost;
    }
}

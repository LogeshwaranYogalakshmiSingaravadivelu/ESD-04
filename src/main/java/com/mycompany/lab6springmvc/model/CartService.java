package com.mycompany.lab6springmvc.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    public List<String> initializeCart() {
        return new ArrayList<>();
    }

    public void addItems(List<String> cart, List<String> items) {
        for (String item : items) {
            if (!cart.contains(item)) {
                cart.add(item);
            }
        }
    }

    public void removeItem(List<String> cart, String item) {
        cart.remove(item);
    }
}


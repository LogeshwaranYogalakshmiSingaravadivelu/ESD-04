package com.mycompany.lab6springmvc.controller;

import com.mycompany.lab6springmvc.model.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class ShoppingCartController {

    private final CartService cartService;

    @Autowired
    public ShoppingCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute("cart")
    public List<String> initializeCart() {
        return cartService.initializeCart();
    }

    @PostMapping
    public String handleCartActions(
            @RequestParam(required = false) String action,
            @RequestParam(required = false) List<String> item,
            @RequestParam(required = false) String removeItem,
            @ModelAttribute("cart") List<String> cart,
            Model model) {

        if ("add".equalsIgnoreCase(action) && item != null) {
            cartService.addItems(cart, item);
        } else if ("remove".equalsIgnoreCase(action) && removeItem != null && !removeItem.isEmpty()) {
            cartService.removeItem(cart, removeItem);
        }

        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping
    public String showCart(Model model, @ModelAttribute("cart") List<String> cart) {
        model.addAttribute("cart", cart);
        return "cart";
    }
}

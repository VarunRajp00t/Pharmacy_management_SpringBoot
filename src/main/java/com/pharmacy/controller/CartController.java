package com.pharmacy.controller;

import com.pharmacy.model.Cart;
import com.pharmacy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 1️ Add Drug to Cart
    @PostMapping("/add")
    public String addDrugToCart(
            @RequestParam String email,
            @RequestParam Long drugId,
            @RequestParam int quantity) {

        return cartService.addDrugToCart(email, drugId, quantity);
    }

    // 2️View Cart Items
    @GetMapping("/view")
    public List<Cart> viewCart(@RequestParam String email) {
        return cartService.viewCart(email);
    }

    // 3️ Checkout Cart
    @PostMapping("/checkout")
    public String checkout(@RequestParam String email) {
        return cartService.checkout(email);
    }
}

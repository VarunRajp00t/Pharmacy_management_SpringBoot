package com.pharmacy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.Cart;
import com.pharmacy.model.Customer;
import com.pharmacy.model.Drug;
import com.pharmacy.model.SalesReceipt;
import com.pharmacy.repository.CartRepository;
import com.pharmacy.repository.CustomerRepository;
import com.pharmacy.repository.DrugRepository;
import com.pharmacy.repository.SalesReceiptRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private SalesReceiptRepository salesReceiptRepository;

    // Add Drug to Cart
    public String addDrugToCart(String email, Long drugId, int quantity) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            return "Customer not found!";
        }

        Drug drug = drugRepository.findById(drugId).orElse(null);
        if (drug == null) {
            return "Drug not found!";
        }

        if (drug.getQuantity() < quantity) {
            return "Not enough stock available!";
        }

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setDrug(drug);
        cart.setQuantity(quantity);
        cartRepository.save(cart);

        return "Drug added to cart successfully!";
    }

    // View Cart
    public List<Cart> viewCart(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        return cartRepository.findByCustomer(customer);
    }

    // Checkout
    
    public String checkout(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            return "Customer not found!";
        }

        List<Cart> cartItems = cartRepository.findByCustomer(customer);
        if (cartItems.isEmpty()) {
            return "Cart is empty!";
        }

        double total = 0;
        for (Cart item : cartItems) {
            Drug drug = item.getDrug();
            drug.setQuantity(drug.getQuantity() - item.getQuantity());
            drugRepository.save(drug);
            total += item.getQuantity() * drug.getPrice();
        }

        // Save Sales Receipt
        SalesReceipt receipt = new SalesReceipt();
        receipt.setCustomer(customer);
        receipt.setTotalAmount(total);
        receipt.setPurchaseDate(LocalDateTime.now());
        receipt.setPaymentMethod("CASH"); // later changeable
        salesReceiptRepository.save(receipt);

        cartRepository.deleteAll(cartItems);

        return "Checkout successful! Total: ₹" + total + 
               "\n Receipt ID: " + receipt.getId();
    }

    }

package com.pharmacy.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pharmacy.model.Cart;
import com.pharmacy.model.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Find all cart items for a specific customer
    List<Cart> findByCustomer(Customer customer);
    void deleteByCustomer(Customer customer);

@Transactional
@Modifying
@Query("DELETE FROM Cart c WHERE c.customer.id = :customerId")
void deleteByCustomerId(@Param("customerId") Long customerId);
}
package com.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "sales_receipt")
public class SalesReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("salesReceipts")  // avoid circular reference
    private Customer customer;

    private double totalAmount;
    private LocalDateTime purchaseDate;
    private String paymentMethod;

    @PrePersist
    public void onCreate() {
        this.purchaseDate = LocalDateTime.now();
    }

    @Transient
    public String getCustomerName() {
        return customer != null ? customer.getName() : "Unknown";
    }
}

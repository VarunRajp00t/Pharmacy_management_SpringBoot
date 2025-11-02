package com.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pharmacy.model.SalesReceipt;

@Repository
public interface SalesReceiptRepository extends JpaRepository<SalesReceipt, Long> {
}

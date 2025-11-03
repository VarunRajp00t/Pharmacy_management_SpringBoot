package com.pharmacy.repository;

import com.pharmacy.model.SalesReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesReceiptRepository extends JpaRepository<SalesReceipt, Long> {
}

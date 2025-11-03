package com.pharmacy.service;

import com.pharmacy.model.SalesReceipt;
import com.pharmacy.repository.SalesReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesReceiptService {

    @Autowired
    private SalesReceiptRepository salesReceiptRepository;

    public List<SalesReceipt> getAllReceipts() {
        return salesReceiptRepository.findAll();
    }

    public SalesReceipt saveReceipt(SalesReceipt receipt) {
        return salesReceiptRepository.save(receipt);
    }

    public void deleteReceipt(Long id) {
        salesReceiptRepository.deleteById(id);
    }

    public SalesReceipt getReceiptById(Long id) {
        return salesReceiptRepository.findById(id).orElse(null);
    }
}

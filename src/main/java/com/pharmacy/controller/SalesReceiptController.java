package com.pharmacy.controller;

import com.pharmacy.model.SalesReceipt;
import com.pharmacy.repository.SalesReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipt")
public class SalesReceiptController {

    @Autowired
    private SalesReceiptRepository receiptRepository;

    @GetMapping("/all")
    public List<SalesReceipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @GetMapping("/{id}")
    public SalesReceipt getReceiptById(@PathVariable Long id) {
        return receiptRepository.findById(id).orElse(null);
    }
}

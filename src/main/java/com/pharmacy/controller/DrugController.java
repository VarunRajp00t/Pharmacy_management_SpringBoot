package com.pharmacy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.model.Drug;
import com.pharmacy.service.DrugService;

@RestController
@RequestMapping("/drugs")
public class DrugController {

    @Autowired
    private DrugService drugService;

    // Add Drug
    @PostMapping
    public Drug addDrug(@RequestBody Drug drug) {
        return drugService.addDrug(drug);
    }

    // Update Drug
    @PutMapping("/{id}")
    public Drug updateDrug(@PathVariable Long id, @RequestBody Drug drug) {
        return drugService.updateDrug(id, drug);
    }

    // Delete Drug
    @DeleteMapping("/{id}")
    public void deleteDrug(@PathVariable Long id) {
        drugService.deleteDrug(id);
    }

    // Get All Drugs
    @GetMapping
    public List<Drug> getAllDrugs() {
        return drugService.getAllDrugs();
    }

    // Get Expired Drugs
    @GetMapping("/expired")
    public List<Drug> getExpiredDrugs() {
        return drugService.getExpiredDrugs();
    }
    
    // Get Single Drug by ID
    @GetMapping("/{id}")
    public Drug getDrugById(@PathVariable Long id) {
        return drugService.getDrugById(id);
    }
}


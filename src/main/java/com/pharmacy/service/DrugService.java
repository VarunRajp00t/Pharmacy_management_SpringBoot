package com.pharmacy.service;


import com.pharmacy.model.Drug;
import com.pharmacy.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DrugService {

    @Autowired
    private DrugRepository drugRepository;

    //  Add Drug
    public Drug addDrug(Drug drug) {
        return drugRepository.save(drug);
    }

    // Update Drug
    public Drug updateDrug(Long id, Drug updatedDrug) {
        Optional<Drug> existing = drugRepository.findById(id);
        if (existing.isPresent()) {
            Drug drug = existing.get();
            drug.setDrugName(updatedDrug.getDrugName());
            drug.setManufacturer(updatedDrug.getManufacturer());
            drug.setExpiryDate(updatedDrug.getExpiryDate());
            drug.setQuantity(updatedDrug.getQuantity());
            drug.setPrice(updatedDrug.getPrice());
            drug.setDescription(updatedDrug.getDescription());
            drug.setTags(updatedDrug.getTags());
            return drugRepository.save(drug);
        }
        return null;
    }

    // Delete Drug
    public void deleteDrug(Long id) {
        drugRepository.deleteById(id);
    }

    // View All Drugs
    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    // Get Expired Drugs
    public List<Drug> getExpiredDrugs() {
        return drugRepository.findByExpiryDateBefore(new Date());
    }
    
   // Get Drug by ID 
    public Drug getDrugById(Long id) {
        return drugRepository.findById(id).orElse(null);
    }
}


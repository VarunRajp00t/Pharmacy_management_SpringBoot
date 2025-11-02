package com.pharmacy.service;

import com.pharmacy.model.Drug;
import com.pharmacy.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private DrugRepository drugRepository;

    public List<String> getAlerts() {
        List<String> alerts = new ArrayList<>();
        List<Drug> drugs = drugRepository.findAll();

        LocalDate today = LocalDate.now();

        for (Drug drug : drugs) {
            // ---- Low Stock Check ----
            Integer quantity = drug.getQuantity();
            if (quantity != null && quantity < 10) {
                alerts.add("Low Stock Alert: " + drug.getDrugName()
                        + " has only " + quantity + " units left.");
            }

            // ---- Expiry Check ----
            Date expiry = drug.getExpiryDate();
            if (expiry != null) {
                try {
                    LocalDate expiryDate = expiry.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

                    if (expiryDate.isBefore(today)) {
                        alerts.add("Expired Drug: " + drug.getDrugName()
                                + " expired on " + expiryDate + ".");
                    }
                } catch (Exception e) {
                    alerts.add("Error reading expiry date for: " + drug.getDrugName());
                }
            } else {
                alerts.add(" No expiry date available for: " + drug.getDrugName());
            }
        }
        return alerts;
    }
}

package com.pharmacy.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.model.Drug;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    Drug findByDrugName(String drugName);
    List<Drug> findByExpiryDateBefore(Date currentDate);  // Expired drugs
}

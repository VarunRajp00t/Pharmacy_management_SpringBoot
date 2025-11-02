package com.pharmacy.controller;

import com.pharmacy.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping
    public List<String> getAlerts() {
        return alertService.getAlerts();
    }
}

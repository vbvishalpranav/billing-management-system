package com.vb.bill.controller;

import com.vb.bill.dto.BillingDTO;
import com.vb.bill.entity.Billing;
import com.vb.bill.service.BillingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping("/create")
    public Billing createBill(@RequestBody BillingDTO billingDTO) {
        log.info("createBill method started");
        Billing billing = billingService.createBill(billingDTO);
        return billing;
    }

    @GetMapping("/get/{id}")
    public Billing getBillingById(@PathVariable Long id) {
        return billingService.getBillingById(id);
    }

    @PutMapping("/update/{id}")
    public Billing updateBillingDetails(@PathVariable Long id, @RequestBody Billing billing) {
        return billingService.updateBillingDetails(id, billing);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteBillingDetails(@PathVariable Long id) {
        return billingService.deleteBillingDetails(id);

    }

}

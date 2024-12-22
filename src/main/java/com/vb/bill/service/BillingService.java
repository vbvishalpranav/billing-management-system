package com.vb.bill.service;

import com.vb.bill.dto.BillingDTO;
import com.vb.bill.entity.Billing;


public interface BillingService {
    Billing createBill(BillingDTO billing);

    Billing getBillingById(Long id);

    Billing updateBillingDetails(Long id, Billing billing);

    String deleteBillingDetails(Long id);
}

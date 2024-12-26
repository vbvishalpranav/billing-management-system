package com.vb.bill.service;

import com.vb.bill.Repo.BillingRepo;
import com.vb.bill.dto.BillingDTO;
import com.vb.bill.entity.Billing;
import com.vb.bill.exception.InvalidFieldException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepo billingRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Billing createBill(BillingDTO billingDTO) {
        log.info("createBill method started");
        Billing billing = modelMapper.map(billingDTO, Billing.class);
        billing.setbName(billingDTO.getName());
        billing.setbCity(billingDTO.getCity());
        Billing saveBilling = billingRepo.save(billing);
        log.info("createBill method end and  saved billing is {}", saveBilling);
        return saveBilling;
    }

    @Override
    public Billing getBillingById(Long id) {
        log.info("getBillingById method started");
        Optional<Billing> optionalBilling = billingRepo.findById(id);
        if (optionalBilling.isPresent()) {

            Billing existingBilling = optionalBilling.get();
            log.info("getBillingById method end and  existing billing is {}", existingBilling);
            return existingBilling;
        }
        log.error("Billing not found for id " + id);
        throw new InvalidFieldException("Billing not found for id " + id);
    }

    @Override
    public Billing updateBillingDetails(Long id, Billing billing) {
        log.info("updateBillingDetails method started");
        Optional<Billing> optionalBilling = billingRepo.findById(id);
        if (optionalBilling.isPresent()) {
            Billing existingBilling = optionalBilling.get();
            existingBilling.setbName(billing.getbName());
            Billing updatedBilling = billingRepo.save(existingBilling);
            log.info("updateBillingDetails method end and  updated billing is {}", updatedBilling);
            return updatedBilling;
        }
        log.error("Billing not found for id " + id);
        throw new InvalidFieldException("Billing not found for id " + id);
    }

    @Override
    public String deleteBillingDetails(Long id) {
        log.info("deleteBillingDetails method started");
        Optional<Billing> existingId = billingRepo.findById(id);
        if (existingId.isPresent()) {
            billingRepo.deleteById(id);
            log.info("deleteBillingDetails method ended");
            return "deletion is successfully";
        }
        log.error("Billing not found for id " + id);
        throw new InvalidFieldException("Billing not found for id " + id);

    }
}
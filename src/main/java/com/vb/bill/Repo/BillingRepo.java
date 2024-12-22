package com.vb.bill.Repo;

import com.vb.bill.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepo extends JpaRepository<Billing, Long> {

}

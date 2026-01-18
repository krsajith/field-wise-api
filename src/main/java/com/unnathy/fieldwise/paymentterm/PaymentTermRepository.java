package com.unnathy.fieldwise.paymentterm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Long> {
}

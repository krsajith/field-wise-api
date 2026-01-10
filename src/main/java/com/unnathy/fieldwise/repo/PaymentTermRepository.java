package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Long> {
}

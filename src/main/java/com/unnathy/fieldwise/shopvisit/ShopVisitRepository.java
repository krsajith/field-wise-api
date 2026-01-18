package com.unnathy.fieldwise.shopvisit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ShopVisitRepository extends JpaRepository<ShopVisit, Long> {
}

package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.ShopVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopVisitRepository extends JpaRepository<ShopVisit, Long> {
}

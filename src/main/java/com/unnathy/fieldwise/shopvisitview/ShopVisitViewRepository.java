package com.unnathy.fieldwise.shopvisitview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ShopVisitViewRepository extends JpaRepository<ShopVisitView, Long> {
}

package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.ShopVisitView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopVisitViewRepository extends JpaRepository<ShopVisitView, Long> {
}

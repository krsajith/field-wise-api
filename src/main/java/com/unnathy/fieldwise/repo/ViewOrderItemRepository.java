package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.OrderItem;
import com.unnathy.fieldwise.entity.ViewOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewOrderItemRepository extends JpaRepository<ViewOrderItem, Long> {
}

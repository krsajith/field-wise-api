package com.unnathy.fieldwise.orderitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteByOrderId(Long orderId);
    List<OrderItem> findByOrderId(Long orderId);
    Optional<OrderItem> findByOrderIdAndProductId(Long orderId, Long productId);
}

package com.unnathy.fieldwise.orderitem;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ViewOrderItemRepository extends JpaRepository<ViewOrderItem, Long> {
    List<ViewOrderItem> findAllByOrderId(Long orderId);
}

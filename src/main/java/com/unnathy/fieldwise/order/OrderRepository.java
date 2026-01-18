package com.unnathy.fieldwise.order;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByStatusOrderByUpdatedAtDesc(String status);

    List<Order> findAllByOrderByUpdatedAtDesc();
}

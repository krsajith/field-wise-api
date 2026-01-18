package com.unnathy.fieldwise.orderview;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface OrderViewRepository extends JpaRepository<OrderView, Long> {
    List<OrderView> findAllByUserId(Long userId);
}

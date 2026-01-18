package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.OrderView;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderViewRepository extends JpaRepository<OrderView, Long> {
    List<OrderView> findAllByUserId(Long userId);
}

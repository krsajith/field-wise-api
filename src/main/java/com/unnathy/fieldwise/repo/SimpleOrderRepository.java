package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.SimpleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleOrderRepository extends JpaRepository<SimpleOrder, Long> {
}

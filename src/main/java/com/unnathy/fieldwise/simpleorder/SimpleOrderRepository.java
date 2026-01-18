package com.unnathy.fieldwise.simpleorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface SimpleOrderRepository extends JpaRepository<SimpleOrder, Long> {
}

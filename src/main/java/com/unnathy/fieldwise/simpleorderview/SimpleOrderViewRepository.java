package com.unnathy.fieldwise.simpleorderview;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface SimpleOrderViewRepository extends JpaRepository<SimpleOrderView, Long> {
    List<SimpleOrderView> findAllByUserId(Long userId);
}

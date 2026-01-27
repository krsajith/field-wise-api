package com.unnathy.fieldwise.orderview;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderViewRepository extends JpaRepository<OrderView, Long> {
    List<OrderView> findAllByUserId(Long userId);

    Page<OrderView> findAllByUserId(Long userId, Pageable pageable);
}

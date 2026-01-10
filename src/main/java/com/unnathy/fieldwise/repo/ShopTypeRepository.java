package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.ShopType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopTypeRepository extends JpaRepository<ShopType, Long> {
}

package com.unnathy.fieldwise.productstock;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
}

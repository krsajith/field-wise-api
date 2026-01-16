package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryIdAndSubCategoryId(Long categoryId,Long subCategoryId);
}

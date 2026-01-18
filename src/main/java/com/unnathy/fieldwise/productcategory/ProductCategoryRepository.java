package com.unnathy.fieldwise.productcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}

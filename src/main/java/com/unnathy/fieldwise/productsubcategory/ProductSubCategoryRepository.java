package com.unnathy.fieldwise.productsubcategory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Long> {
    List<ProductSubCategory> findByCategoryId(Long categoryId);
}

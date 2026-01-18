package com.unnathy.fieldwise.leavecategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface LeaveCategoryRepository extends JpaRepository<LeaveCategory, Long> {
}

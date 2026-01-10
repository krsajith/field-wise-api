package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.LeaveCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveCategoryRepository extends JpaRepository<LeaveCategory, Long> {
}

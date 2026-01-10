package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.FieldWiseApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldWiseApplicationRepository extends JpaRepository<FieldWiseApplication, Long> {
}

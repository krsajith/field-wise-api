package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.Expens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensRepository extends JpaRepository<Expens, Long> {
}

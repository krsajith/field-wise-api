package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.TransportationMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportationModeRepository extends JpaRepository<TransportationMode, Long> {
}

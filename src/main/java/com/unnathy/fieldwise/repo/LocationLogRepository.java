package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.LocationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationLogRepository extends JpaRepository<LocationLog, Long> {
}

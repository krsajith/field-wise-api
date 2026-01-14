package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.Attendance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findTopByUserIdOrderByIdDesc(Long userId);
}

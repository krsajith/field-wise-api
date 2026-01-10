package com.unnathy.fieldwise.repo;

import com.unnathy.fieldwise.entity.UserRouteAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRouteAssignmentRepository extends JpaRepository<UserRouteAssignment, Long> {
}

package com.unnathy.fieldwise.userrouteassignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserRouteAssignmentRepository extends JpaRepository<UserRouteAssignment, Long> {
}

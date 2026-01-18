package com.unnathy.fieldwise.leaveapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
}

package com.unnathy.fieldwise.userdevice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {
}

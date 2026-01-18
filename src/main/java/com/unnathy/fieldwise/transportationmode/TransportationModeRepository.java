package com.unnathy.fieldwise.transportationmode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface TransportationModeRepository extends JpaRepository<TransportationMode, Long> {
}

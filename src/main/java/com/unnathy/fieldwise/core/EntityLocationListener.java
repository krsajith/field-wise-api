package com.unnathy.fieldwise.core;

import com.unnathy.fieldwise.entity.BaseEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.math.BigDecimal;



public class EntityLocationListener {

    @PrePersist
    @PreUpdate
    public void applyLocation(Object entity) {
        if (!(entity instanceof BaseEntity baseEntity)) {
            return;
        }
        BigDecimal latitude = LocationContext.getLatitude();
        BigDecimal longitude = LocationContext.getLongitude();
        if (latitude != null) {
            baseEntity.setLatitude(latitude);
        }
        if (longitude != null) {
            baseEntity.setLongitude(longitude);
        }
    }
}

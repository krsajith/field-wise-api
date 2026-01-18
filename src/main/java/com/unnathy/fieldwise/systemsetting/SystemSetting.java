package com.unnathy.fieldwise.systemsetting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import com.unnathy.fieldwise.entity.BaseEntity;



@Getter
@Setter
@Entity
@Table(name = "system_settings")
public class SystemSetting extends BaseEntity {

    @Column(name = "setting_key", nullable = false, length = 100)
    private String settingKey;

    @Column(name = "setting_value", length = Integer.MAX_VALUE)
    private String settingValue;

    @Column(name = "data_type", length = 50)
    private String dataType;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ColumnDefault("false")
    @Column(name = "is_public")
    private Boolean isPublic;

}

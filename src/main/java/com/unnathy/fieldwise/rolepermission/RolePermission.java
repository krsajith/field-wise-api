package com.unnathy.fieldwise.rolepermission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import com.unnathy.fieldwise.entity.BaseEntity;



@Getter
@Setter
@Entity
@Table(name = "role_permissions")
public class RolePermission extends BaseEntity {

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

}

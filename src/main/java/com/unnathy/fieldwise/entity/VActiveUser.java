package com.unnathy.fieldwise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.Instant;

@Getter
@Entity
@Immutable
@Table(name = "v_active_users")
public class VActiveUser {
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "employee_code", length = 50)
    private String employeeCode;

    @Column(name = "role_name", length = 50)
    private String roleName;

    @Column(name = "manager_name", length = Integer.MAX_VALUE)
    private String managerName;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "last_login_at")
    private Instant lastLoginAt;


}
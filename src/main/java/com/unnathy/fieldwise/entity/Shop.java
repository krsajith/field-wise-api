package com.unnathy.fieldwise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "shop_type_id")
    private Long shopType;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "alternate_phone", length = 20)
    private String alternatePhone;

    @Column(name = "email")
    private String email;

    @Column(name = "whatsapp_number", length = 20)
    private String whatsappNumber;

    @Column(name = "gst_number", length = 50)
    private String gstNumber;

    @Column(name = "pan_number", length = 50)
    private String panNumber;

    @Column(name = "aadhar_number", length = 50)
    private String aadharNumber;

    @Column(name = "address_line1", length = 500)
    private String addressLine1;

    @Column(name = "address_line2", length = 500)
    private String addressLine2;

    @Column(name = "place_id")
    private Long place;

    @Column(name = "district_id")
    private Long district;

    @Column(name = "state_id")
    private Long state;

    @Column(name = "pincode", length = 10)
    private String pincode;

    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    @ColumnDefault("100")
    @Column(name = "geofence_radius")
    private Integer geofenceRadius;

    @Column(name = "route_id")
    private Long route;

    @Column(name = "bank_id")
    private Long bank;

    @Column(name = "bank_account_number", length = 50)
    private String bankAccountNumber;

    @Column(name = "bank_ifsc_code", length = 20)
    private String bankIfscCode;

    @Column(name = "payment_term_id")
    private Long paymentTerm;

    @Column(name = "credit_limit", precision = 12, scale = 2)
    private BigDecimal creditLimit;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

    @ColumnDefault("false")
    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "verified_by")
    private Long verifiedBy;

    @Column(name = "shop_photo_url", length = 500)
    private String shopPhotoUrl;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

}

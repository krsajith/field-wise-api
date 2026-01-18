package com.unnathy.fieldwise.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vw_order_items")
public class ViewOrderItem  extends OrderItem{
    private String productName;
}

package com.excoder.orderservice.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document("order")
public class Order {

    @MongoId(FieldType.OBJECT_ID)
    private Integer id;
    private Integer customerId;
    @Indexed
    private String status;
    @Indexed
    private String address;
    private BigDecimal orderTotal;
    private LocalDate createdDate;

    public Order() {
    }

    public Order(Integer id, Integer customerId, String status, String address, BigDecimal orderTotal, LocalDate createdDate) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.address = address;
        this.orderTotal = orderTotal;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}


package com.dm.customer.model;

import jakarta.persistence.*;

/**
 * CDMModel model class
 */
@Entity
@Table(name = "customer")
public class CDMModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_mobile_number")
    private String customerMobileNumber;

    @Column(name = "customer_pan_card_number")
    private String customerPanCardNumber;

    @Column(name = "customer_active_status")
    private String customerActiveStatus;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;

    public CDMModel() {
    }

    public CDMModel(int customerId, String customerName, String customerMobileNumber, String customerPanCardNumber, String customerActiveStatus, String customerAddress, String createdDate, String createdBy, String updatedDate, String updatedBy) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerMobileNumber = customerMobileNumber;
        this.customerPanCardNumber = customerPanCardNumber;
        this.customerActiveStatus = customerActiveStatus;
        this.customerAddress = customerAddress;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }

    public String getCustomerPanCardNumber() {
        return customerPanCardNumber;
    }

    public void setCustomerPanCardNumber(String customerPanCardNumber) {
        this.customerPanCardNumber = customerPanCardNumber;
    }

    public String getCustomerActiveStatus() {
        return customerActiveStatus;
    }

    public void setCustomerActiveStatus(String customerActiveStatus) {
        this.customerActiveStatus = customerActiveStatus;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}

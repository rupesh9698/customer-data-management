package com.dm.customer.dto;

/**
 * Customer details class used in customer search response
 */
public class CustomerDetails {

    private String customerId;
    private String customerName;
    private String customerMobileNumber;
    private String customerPanCardNumber;
    private String customerActiveStatus;
    private String customerAddress;
    private String createdDate;
    private String createdBy;
    private String updatedDate;
    private String updatedBy;

    public CustomerDetails() {
    }

    public CustomerDetails(String customerId, String customerName, String customerMobileNumber, String customerPanCardNumber, String customerActiveStatus, String customerAddress, String createdDate, String createdBy, String updatedDate, String updatedBy) {
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
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

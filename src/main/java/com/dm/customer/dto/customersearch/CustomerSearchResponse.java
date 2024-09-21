package com.dm.customer.dto.customersearch;

import com.dm.customer.dto.CustomerDetails;
import com.dm.customer.exception.RestApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Customer search response model class
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerSearchResponse implements RestApiResponse {

    private String status;
    private List<CustomerDetails> data;

    public CustomerSearchResponse() {
    }

    public CustomerSearchResponse(String status, List<CustomerDetails> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomerDetails> getData() {
        return data;
    }

    public void setData(List<CustomerDetails> data) {
        this.data = data;
    }
}

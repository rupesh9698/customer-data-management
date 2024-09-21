package com.dm.customer.dto.userlogin;

import com.dm.customer.dto.UserDetails;
import com.dm.customer.exception.RestApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Login response model class
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRegisterResponse implements RestApiResponse {

    private String status;
    private List<UserDetails> data;

    public LoginRegisterResponse() {
    }

    public LoginRegisterResponse(String status, List<UserDetails> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserDetails> getData() {
        return data;
    }

    public void setData(List<UserDetails> data) {
        this.data = data;
    }
}

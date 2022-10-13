package com.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FraudCheckResponse {
    Boolean isFraudster;

    public Boolean getFraudster() {
        return isFraudster;
    }

    public void setFraudster(Boolean fraudster) {
        isFraudster = fraudster;
    }
}

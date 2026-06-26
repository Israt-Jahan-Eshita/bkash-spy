package com.bkash.spy.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Department {
    CUSTOMER_SUPPORT("customer_support"),
    DISPUTE_RESOLUTION("dispute_resolution"),
    FRAUD_RISK("fraud_risk"),
    TECHNICAL_SUPPORT("technical_support"),
    ACCOUNT_MANAGEMENT("account_management"),
    OTHER("other");

    private final String value;

    Department(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

package com.bkash.spy.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Department {
    CUSTOMER_SUPPORT("customer_support"),
    DISPUTE_RESOLUTION("dispute_resolution"),
    PAYMENTS_OPS("payments_ops"),
    MERCHANT_OPERATIONS("merchant_operations"),
    AGENT_OPERATIONS("agent_operations"),
    FRAUD_RISK("fraud_risk");

    private final String value;

    Department(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

package com.bkash.spy.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CaseType {
    WRONG_TRANSFER("wrong_transfer"),
    PAYMENT_FAILED("payment_failed"),
    REFUND_REQUEST("refund_request"),
    DUPLICATE_PAYMENT("duplicate_payment"),
    MERCHANT_SETTLEMENT_DELAY("merchant_settlement_delay"),
    AGENT_CASH_IN_ISSUE("agent_cash_in_issue"),
    PHISHING_OR_SOCIAL_ENGINEERING("phishing_or_social_engineering"),
    OTHER("other");

    private final String value;

    CaseType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

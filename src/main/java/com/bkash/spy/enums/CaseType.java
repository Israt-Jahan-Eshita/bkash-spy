package com.bkash.spy.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CaseType {
    WRONG_TRANSFER("wrong_transfer"),
    CASH_OUT_ISSUE("cash_out_issue"),
    ACCOUNT_COMPROMISE("account_compromise"),
    PHISHING_OR_SOCIAL_ENGINEERING("phishing_or_social_engineering"),
    PAYMENT_FAILURE("payment_failure"),
    MERCHANT_DISPUTE("merchant_dispute"),
    APP_BUG("app_bug"),
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

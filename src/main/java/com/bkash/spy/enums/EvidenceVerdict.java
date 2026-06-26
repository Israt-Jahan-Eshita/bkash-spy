package com.bkash.spy.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EvidenceVerdict {
    CONSISTENT("consistent"),
    INCONSISTENT("inconsistent"),
    INSUFFICIENT_DATA("insufficient_data");

    private final String value;

    EvidenceVerdict(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

package com.bkash.spy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class TransactionDto {
    @JsonProperty("transaction_id")
    private String transactionId;
    private String timestamp;
    private String type;
    private BigDecimal amount;
    private String counterparty;
    private String status;

    public TransactionDto() {}

    public TransactionDto(String transactionId, String timestamp, String type, BigDecimal amount, String counterparty, String status) {
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
        this.counterparty = counterparty;
        this.status = status;
    }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCounterparty() { return counterparty; }
    public void setCounterparty(String counterparty) { this.counterparty = counterparty; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

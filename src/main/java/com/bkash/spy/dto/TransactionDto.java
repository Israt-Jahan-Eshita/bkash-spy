package com.bkash.spy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    @JsonProperty("transaction_id")
    private String transactionId;
    
    private String timestamp;
    private String type;
    private BigDecimal amount;
    private String counterparty;
    private String status;
}

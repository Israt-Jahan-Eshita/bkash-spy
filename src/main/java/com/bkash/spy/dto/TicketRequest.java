package com.bkash.spy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {
    
    @NotBlank(message = "ticket_id is required")
    @JsonProperty("ticket_id")
    private String ticketId;

    @NotBlank(message = "complaint is required")
    private String complaint;

    private String language;
    
    private String channel;
    
    @JsonProperty("user_type")
    private String userType;
    
    @JsonProperty("campaign_context")
    private String campaignContext;
    
    @JsonProperty("transaction_history")
    private List<TransactionDto> transactionHistory;
    
    private Map<String, Object> metadata;
}

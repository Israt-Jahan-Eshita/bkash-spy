package com.bkash.spy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

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

    public TicketRequest() {}

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
    public String getComplaint() { return complaint; }
    public void setComplaint(String complaint) { this.complaint = complaint; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    public String getCampaignContext() { return campaignContext; }
    public void setCampaignContext(String campaignContext) { this.campaignContext = campaignContext; }
    public List<TransactionDto> getTransactionHistory() { return transactionHistory; }
    public void setTransactionHistory(List<TransactionDto> transactionHistory) { this.transactionHistory = transactionHistory; }
    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
}

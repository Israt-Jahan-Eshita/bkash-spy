package com.bkash.spy.dto;

import com.bkash.spy.enums.CaseType;
import com.bkash.spy.enums.Department;
import com.bkash.spy.enums.EvidenceVerdict;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TicketResponse {

    @JsonProperty("ticket_id")
    private String ticketId;

    @JsonProperty("relevant_transaction_id")
    private String relevantTransactionId;

    @JsonProperty("evidence_verdict")
    private EvidenceVerdict evidenceVerdict;

    @JsonProperty("case_type")
    private CaseType caseType;

    private String severity;
    private Department department;

    @JsonProperty("agent_summary")
    private String agentSummary;

    @JsonProperty("recommended_next_action")
    private String recommendedNextAction;

    @JsonProperty("customer_reply")
    private String customerReply;

    @JsonProperty("human_review_required")
    private boolean humanReviewRequired;

    private Double confidence;

    @JsonProperty("reason_codes")
    private List<String> reasonCodes;

    public TicketResponse() {}

    // Getters
    public String getTicketId() { return ticketId; }
    public String getRelevantTransactionId() { return relevantTransactionId; }
    public EvidenceVerdict getEvidenceVerdict() { return evidenceVerdict; }
    public CaseType getCaseType() { return caseType; }
    public String getSeverity() { return severity; }
    public Department getDepartment() { return department; }
    public String getAgentSummary() { return agentSummary; }
    public String getRecommendedNextAction() { return recommendedNextAction; }
    public String getCustomerReply() { return customerReply; }
    public boolean isHumanReviewRequired() { return humanReviewRequired; }
    public Double getConfidence() { return confidence; }
    public List<String> getReasonCodes() { return reasonCodes; }

    // Setters
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
    public void setRelevantTransactionId(String relevantTransactionId) { this.relevantTransactionId = relevantTransactionId; }
    public void setEvidenceVerdict(EvidenceVerdict evidenceVerdict) { this.evidenceVerdict = evidenceVerdict; }
    public void setCaseType(CaseType caseType) { this.caseType = caseType; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setDepartment(Department department) { this.department = department; }
    public void setAgentSummary(String agentSummary) { this.agentSummary = agentSummary; }
    public void setRecommendedNextAction(String recommendedNextAction) { this.recommendedNextAction = recommendedNextAction; }
    public void setCustomerReply(String customerReply) { this.customerReply = customerReply; }
    public void setHumanReviewRequired(boolean humanReviewRequired) { this.humanReviewRequired = humanReviewRequired; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }
    public void setReasonCodes(List<String> reasonCodes) { this.reasonCodes = reasonCodes; }

    // Static Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final TicketResponse obj = new TicketResponse();
        public Builder ticketId(String v) { obj.ticketId = v; return this; }
        public Builder relevantTransactionId(String v) { obj.relevantTransactionId = v; return this; }
        public Builder evidenceVerdict(EvidenceVerdict v) { obj.evidenceVerdict = v; return this; }
        public Builder caseType(CaseType v) { obj.caseType = v; return this; }
        public Builder severity(String v) { obj.severity = v; return this; }
        public Builder department(Department v) { obj.department = v; return this; }
        public Builder agentSummary(String v) { obj.agentSummary = v; return this; }
        public Builder recommendedNextAction(String v) { obj.recommendedNextAction = v; return this; }
        public Builder customerReply(String v) { obj.customerReply = v; return this; }
        public Builder humanReviewRequired(boolean v) { obj.humanReviewRequired = v; return this; }
        public Builder confidence(Double v) { obj.confidence = v; return this; }
        public Builder reasonCodes(List<String> v) { obj.reasonCodes = v; return this; }
        public TicketResponse build() { return obj; }
    }
}

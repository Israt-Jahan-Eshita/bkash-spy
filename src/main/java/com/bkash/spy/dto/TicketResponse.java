package com.bkash.spy.dto;

import com.bkash.spy.enums.CaseType;
import com.bkash.spy.enums.Department;
import com.bkash.spy.enums.EvidenceVerdict;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}

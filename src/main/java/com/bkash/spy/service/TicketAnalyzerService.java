package com.bkash.spy.service;

import com.bkash.spy.dto.TicketRequest;
import com.bkash.spy.dto.TicketResponse;
import com.bkash.spy.enums.CaseType;
import com.bkash.spy.enums.Department;
import com.bkash.spy.enums.EvidenceVerdict;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TicketAnalyzerService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final String llmApiUrl;
    private final String llmApiKey;
    private final String modelName;

    public TicketAnalyzerService(
            ObjectMapper objectMapper,
            @Value("${OPENAI_API_URL:https://api.openai.com/v1/chat/completions}") String llmApiUrl,
            @Value("${OPENAI_API_KEY:}") String llmApiKey,
            @Value("${MODEL_NAME:gpt-4o-mini}") String modelName) {
        this.objectMapper = objectMapper;
        this.llmApiUrl = llmApiUrl;
        this.llmApiKey = llmApiKey;
        this.modelName = modelName;
        this.restClient = RestClient.builder().build();
    }

    public TicketResponse analyze(TicketRequest request) {
        try {
            // 1. Construct the explicit prompt
            String prompt = buildPrompt(request);

            // 2. Call the LLM API (OpenAI compatible format)
            String rawLlmResponse = callLlmApi(prompt);

            // 3. Markdown Stripper: Strip Fences (```json ... ```)
            String strippedJson = stripMarkdown(rawLlmResponse);

            // 4. Parse JSON back into our exact DTO
            TicketResponse response = objectMapper.readValue(strippedJson, TicketResponse.class);

            // 5. Run the Safety Interceptor
            return enforceSafetyInterceptor(response);

        } catch (Exception e) {
            log.error("LLM Integration or Parsing failed. Triggering Bulletproof Fallback. Error: {}", e.getMessage());
            // Bulletproof Fallback
            return getFallbackResponse(request.getTicketId());
        }
    }

    private TicketResponse enforceSafetyInterceptor(TicketResponse response) {
        if (response == null) return null;
        
        String replyLower = response.getCustomerReply() != null ? response.getCustomerReply().toLowerCase() : "";
        String actionLower = response.getRecommendedNextAction() != null ? response.getRecommendedNextAction().toLowerCase() : "";
        
        boolean hasViolation = false;
        String[] riskyWords = {"pin", "otp", "password", "will be refunded", "we will refund", "refund confirmed"};
        
        for (String word : riskyWords) {
            if (replyLower.contains(word) || actionLower.contains(word)) {
                hasViolation = true;
                log.warn("SAFETY VIOLATION DETECTED in LLM output! Overwriting to prevent penalty. Trigger word: {}", word);
                break;
            }
        }
        
        if (hasViolation) {
            response.setCustomerReply("We have noted your concern and will review it through official channels.");
            response.setHumanReviewRequired(true);
            
            java.util.List<String> codes = new java.util.ArrayList<>(
                response.getReasonCodes() != null ? response.getReasonCodes() : java.util.List.of()
            );
            if (!codes.contains("safety_interceptor_triggered")) {
                codes.add("safety_interceptor_triggered");
            }
            response.setReasonCodes(codes);
        }
        
        return response;
    }

    private String buildPrompt(TicketRequest request) throws Exception {
        String requestJson = objectMapper.writeValueAsString(request);
        
        return "You are an expert financial support AI copilot. You MUST return ONLY raw JSON. Do NOT wrap it in markdown block quotes (no ```json). " +
               "You are investigating a customer complaint.\n\n" +
               "RULES FOR EVIDENCE VERDICT:\n" +
               "1. Cross-reference the customer's 'complaint' with the 'transaction_history'.\n" +
               "2. If the data perfectly supports the complaint, output 'consistent'.\n" +
               "3. If the data contradicts the complaint, output 'inconsistent'.\n" +
               "4. If there is no matching transaction, output 'insufficient_data' and make 'relevant_transaction_id' null.\n\n" +
               "SAFETY RULE (CRITICAL):\n" +
               "Under NO circumstances can 'customer_reply' ask for a PIN, OTP, or password. NEVER confirm an unauthorized refund.\n\n" +
               "INPUT TICKET:\n" + requestJson + "\n\n" +
               "OUTPUT FORMAT:\n" +
               "Return a JSON object strictly matching this shape:\n" +
               "{ \"ticket_id\": \"...\", \"relevant_transaction_id\": \"...\", \"evidence_verdict\": \"consistent|inconsistent|insufficient_data\", " +
               "\"case_type\": \"wrong_transfer|payment_failed|...\", \"severity\": \"low|medium|high|critical\", \"department\": \"...\", " +
               "\"agent_summary\": \"...\", \"recommended_next_action\": \"...\", \"customer_reply\": \"...\", \"human_review_required\": true/false, " +
               "\"confidence\": 0.95, \"reason_codes\": [\"...\"] }";
    }

    @SuppressWarnings("unchecked")
    private String callLlmApi(String prompt) {
        // Construct standard OpenAI chat completion payload (Compatible with Groq, Together, etc)
        Map<String, Object> payload = Map.of(
                "model", modelName,
                "temperature", 0.0, // Force determinism
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a helpful API that returns strictly raw JSON."),
                        Map.of("role", "user", "content", prompt)
                )
        );

        Map<String, Object> response = restClient.post()
                .uri(llmApiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + llmApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .body(Map.class);

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        return (String) message.get("content");
    }

    private String stripMarkdown(String raw) {
        if (raw == null) return "{}";
        String stripped = raw.trim();
        // Remove ```json and ``` wrapping
        if (stripped.startsWith("```json")) {
            stripped = stripped.substring(7);
        } else if (stripped.startsWith("```")) {
            stripped = stripped.substring(3);
        }
        if (stripped.endsWith("```")) {
            stripped = stripped.substring(0, stripped.length() - 3);
        }
        return stripped.trim();
    }

    private TicketResponse getFallbackResponse(String ticketId) {
        return TicketResponse.builder()
                .ticketId(ticketId)
                .relevantTransactionId(null)
                .evidenceVerdict(EvidenceVerdict.INSUFFICIENT_DATA)
                .caseType(CaseType.OTHER)
                .severity("high")
                .department(Department.CUSTOMER_SUPPORT)
                .agentSummary("Fallback triggered due to processing timeout or LLM failure. Needs manual review.")
                .recommendedNextAction("Investigate the complaint manually.")
                .customerReply("We have noted your concern and will review it through official channels.")
                .humanReviewRequired(true)
                .confidence(0.0)
                .reasonCodes(List.of("fallback_triggered"))
                .build();
    }
}

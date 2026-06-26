package com.bkash.spy.controller;

import com.bkash.spy.dto.TicketRequest;
import com.bkash.spy.dto.TicketResponse;
import com.bkash.spy.service.TicketAnalyzerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketAnalyzerService ticketAnalyzerService;

    @PostMapping("/analyze-ticket")
    public ResponseEntity<TicketResponse> analyzeTicket(@Valid @RequestBody TicketRequest request) {
        TicketResponse response = ticketAnalyzerService.analyze(request);
        return ResponseEntity.ok(response);
    }
}

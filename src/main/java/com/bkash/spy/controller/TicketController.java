package com.bkash.spy.controller;

import com.bkash.spy.dto.TicketRequest;
import com.bkash.spy.dto.TicketResponse;
import com.bkash.spy.service.TicketAnalyzerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    private final TicketAnalyzerService ticketAnalyzerService;

    public TicketController(TicketAnalyzerService ticketAnalyzerService) {
        this.ticketAnalyzerService = ticketAnalyzerService;
    }

    @PostMapping("/analyze-ticket")
    public ResponseEntity<TicketResponse> analyzeTicket(@Valid @RequestBody TicketRequest request) {
        TicketResponse response = ticketAnalyzerService.analyze(request);
        return ResponseEntity.ok(response);
    }
}

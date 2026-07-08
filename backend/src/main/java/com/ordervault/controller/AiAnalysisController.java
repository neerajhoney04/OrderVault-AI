package com.ordervault.controller;

import com.ordervault.dto.ApiResponse;
import com.ordervault.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AiAnalysisController {
    
    private final GeminiService geminiService;
    
    @PostMapping("/analyze-order")
    public ResponseEntity<ApiResponse<String>> analyzeOrder(@RequestBody String orderDetails) {
        String analysis = geminiService.analyzeOrder(orderDetails);
        return ResponseEntity.ok(ApiResponse.success("Order analysis completed", analysis));
    }
    
    @PostMapping("/extract-order-info")
    public ResponseEntity<ApiResponse<String>> extractOrderInfo(@RequestBody String text) {
        String extractedInfo = geminiService.extractOrderInfo(text);
        return ResponseEntity.ok(ApiResponse.success("Order information extracted", extractedInfo));
    }
    
    @PostMapping("/order-summary")
    public ResponseEntity<ApiResponse<String>> getOrderSummary(@RequestBody String orderDetails) {
        String summary = geminiService.getOrderSummary(orderDetails);
        return ResponseEntity.ok(ApiResponse.success("Order summary generated", summary));
    }
    
    @PostMapping("/order-insights")
    public ResponseEntity<ApiResponse<String>> generateOrderInsights(@RequestBody String orderDetails) {
        String insights = geminiService.generateOrderInsights(orderDetails);
        return ResponseEntity.ok(ApiResponse.success("Order insights generated", insights));
    }
}

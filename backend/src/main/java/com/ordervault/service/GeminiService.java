package com.ordervault.service;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeAIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeminiService {
    
    @Value("${app.gemini.api-key}")
    private String apiKey;
    
    @Value("${app.gemini.model}")
    private String modelName;
    
    private GenerativeModel model;
    
    public String analyzeOrder(String orderDetails) {
        try {
            if (model == null) {
                model = new GenerativeModel(modelName, apiKey);
            }
            
            String prompt = buildOrderAnalysisPrompt(orderDetails);
            var response = model.generateContent(prompt);
            
            return response.getText();
        } catch (GenerativeAIException e) {
            log.error("Error calling Gemini API for order analysis", e);
            throw new RuntimeException("Failed to analyze order with AI", e);
        }
    }
    
    public String extractOrderInfo(String text) {
        try {
            if (model == null) {
                model = new GenerativeModel(modelName, apiKey);
            }
            
            String prompt = "Extract order information from the following text and format it as JSON with fields: orderId, productName, brand, seller, category, quantity, price, orderDate, platform. Text: " + text;
            var response = model.generateContent(prompt);
            
            return response.getText();
        } catch (GenerativeAIException e) {
            log.error("Error calling Gemini API for order extraction", e);
            throw new RuntimeException("Failed to extract order info with AI", e);
        }
    }
    
    public String getOrderSummary(String orderDetails) {
        try {
            if (model == null) {
                model = new GenerativeModel(modelName, apiKey);
            }
            
            String prompt = "Generate a brief summary of the following order details in 2-3 sentences: " + orderDetails;
            var response = model.generateContent(prompt);
            
            return response.getText();
        } catch (GenerativeAIException e) {
            log.error("Error calling Gemini API for order summary", e);
            throw new RuntimeException("Failed to generate order summary with AI", e);
        }
    }
    
    public String generateOrderInsights(String orderDetails) {
        try {
            if (model == null) {
                model = new GenerativeModel(modelName, apiKey);
            }
            
            String prompt = "Based on the following order details, provide insights about purchase patterns, recommendations, and any anomalies: " + orderDetails;
            var response = model.generateContent(prompt);
            
            return response.getText();
        } catch (GenerativeAIException e) {
            log.error("Error calling Gemini API for insights generation", e);
            throw new RuntimeException("Failed to generate insights with AI", e);
        }
    }
    
    private String buildOrderAnalysisPrompt(String orderDetails) {
        return "Please analyze the following order details and provide comprehensive insights including: " +
                "1. Product quality assessment\n" +
                "2. Price value analysis\n" +
                "3. Seller credibility assessment\n" +
                "4. Delivery status prediction\n" +
                "5. Return risk assessment\n\n" +
                "Order Details:\n" + orderDetails;
    }
}

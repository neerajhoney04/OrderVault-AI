package com.ordervault.entity;

public enum OrderStatus {
    PENDING("Pending"),
    DELIVERED("Delivered"),
    RETURNED("Returned"),
    CANCELLED("Cancelled"),
    PROCESSING("Processing"),
    SHIPPED("Shipped");
    
    private final String displayName;
    
    OrderStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}

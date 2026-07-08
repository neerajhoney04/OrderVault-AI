package com.ordervault.dto;

import com.ordervault.entity.OrderStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    
    @NotBlank(message = "Platform is required")
    private String platform;
    
    @NotBlank(message = "Order ID is required")
    private String orderId;
    
    @NotBlank(message = "Product name is required")
    private String productName;
    
    @NotBlank(message = "Brand is required")
    private String brand;
    
    @NotBlank(message = "Seller is required")
    private String seller;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
    
    @NotNull(message = "Order date is required")
    private LocalDate orderDate;
    
    private LocalDate deliveryDate;
    
    private LocalDate returnDate;
    
    @NotNull(message = "Status is required")
    private OrderStatus status;
    
    private String imageUrl;
    
    private String notes;
}

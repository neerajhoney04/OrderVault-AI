package com.ordervault.dto;

import com.ordervault.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String platform;
    private String orderId;
    private String productName;
    private String brand;
    private String seller;
    private String category;
    private Integer quantity;
    private BigDecimal price;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private LocalDate returnDate;
    private OrderStatus status;
    private String imageUrl;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

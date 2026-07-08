package com.ordervault.controller;

import com.ordervault.dto.ApiResponse;
import com.ordervault.dto.OrderDTO;
import com.ordervault.dto.OrderRequest;
import com.ordervault.entity.OrderStatus;
import com.ordervault.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<OrderDTO>> createOrder(@Valid @RequestBody OrderRequest request) {
        OrderDTO orderDTO = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Order created successfully", orderDTO));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDTO>> getOrderById(@PathVariable String id) {
        OrderDTO orderDTO = orderService.getOrderById(id);
        return ResponseEntity.ok(ApiResponse.success("Order retrieved successfully", orderDTO));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(ApiResponse.success("Orders retrieved successfully", orders));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDTO>> updateOrder(
            @PathVariable String id,
            @Valid @RequestBody OrderRequest request) {
        OrderDTO orderDTO = orderService.updateOrder(id, request);
        return ResponseEntity.ok(ApiResponse.success("Order updated successfully", orderDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(ApiResponse.success("Order deleted successfully", null));
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> searchOrders(@RequestParam String query) {
        List<OrderDTO> orders = orderService.searchOrders(query);
        return ResponseEntity.ok(ApiResponse.success("Search results retrieved successfully", orders));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Orders retrieved by status successfully", orders));
    }
    
    @GetMapping("/platform/{platform}")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByPlatform(@PathVariable String platform) {
        List<OrderDTO> orders = orderService.getOrdersByPlatform(platform);
        return ResponseEntity.ok(ApiResponse.success("Orders retrieved by platform successfully", orders));
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<OrderDTO> orders = orderService.getOrdersByDateRange(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success("Orders retrieved by date range successfully", orders));
    }
    
    @GetMapping("/returns/upcoming")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getUpcomingReturns(
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {
        List<OrderDTO> orders = orderService.getUpcomingReturns(fromDate);
        return ResponseEntity.ok(ApiResponse.success("Upcoming returns retrieved successfully", orders));
    }
    
    @GetMapping("/stats/count/{status}")
    public ResponseEntity<ApiResponse<Long>> getOrderCountByStatus(@PathVariable OrderStatus status) {
        long count = orderService.getOrderCountByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Order count retrieved successfully", count));
    }
    
    @GetMapping("/stats/total-spent")
    public ResponseEntity<ApiResponse<Double>> getTotalSpent(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Double totalSpent = orderService.getTotalSpentBetweenDates(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success("Total spent calculated successfully", totalSpent));
    }
}

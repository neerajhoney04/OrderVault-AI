package com.ordervault.service;

import com.ordervault.dto.OrderDTO;
import com.ordervault.dto.OrderRequest;
import com.ordervault.entity.Order;
import com.ordervault.entity.OrderStatus;
import com.ordervault.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    
    private final OrderRepository orderRepository;
    
    public OrderDTO createOrder(OrderRequest request) {
        Order order = Order.builder()
                .platform(request.getPlatform())
                .orderId(request.getOrderId())
                .productName(request.getProductName())
                .brand(request.getBrand())
                .seller(request.getSeller())
                .category(request.getCategory())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .orderDate(request.getOrderDate())
                .deliveryDate(request.getDeliveryDate())
                .returnDate(request.getReturnDate())
                .status(request.getStatus())
                .imageUrl(request.getImageUrl())
                .notes(request.getNotes())
                .build();
        
        Order savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder);
    }
    
    public OrderDTO updateOrder(String id, OrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        
        order.setPlatform(request.getPlatform());
        order.setOrderId(request.getOrderId());
        order.setProductName(request.getProductName());
        order.setBrand(request.getBrand());
        order.setSeller(request.getSeller());
        order.setCategory(request.getCategory());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setOrderDate(request.getOrderDate());
        order.setDeliveryDate(request.getDeliveryDate());
        order.setReturnDate(request.getReturnDate());
        order.setStatus(request.getStatus());
        order.setImageUrl(request.getImageUrl());
        order.setNotes(request.getNotes());
        
        Order updatedOrder = orderRepository.save(order);
        return mapToDTO(updatedOrder);
    }
    
    public OrderDTO getOrderById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return mapToDTO(order);
    }
    
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> searchOrders(String searchTerm) {
        return orderRepository.searchOrders(searchTerm).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findOrdersByStatusOrderByDate(status).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getOrdersByPlatform(String platform) {
        return orderRepository.findByPlatform(platform).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getUpcomingReturns(LocalDate fromDate) {
        return orderRepository.findUpcomingReturns(fromDate).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public void deleteOrder(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        orderRepository.delete(order);
    }
    
    public long getOrderCountByStatus(OrderStatus status) {
        return orderRepository.countByStatus(status);
    }
    
    public Double getTotalSpentBetweenDates(LocalDate startDate, LocalDate endDate) {
        return orderRepository.getTotalSpentBetweenDates(startDate, endDate)
                .orElse(0.0);
    }
    
    private OrderDTO mapToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .platform(order.getPlatform())
                .orderId(order.getOrderId())
                .productName(order.getProductName())
                .brand(order.getBrand())
                .seller(order.getSeller())
                .category(order.getCategory())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .orderDate(order.getOrderDate())
                .deliveryDate(order.getDeliveryDate())
                .returnDate(order.getReturnDate())
                .status(order.getStatus())
                .imageUrl(order.getImageUrl())
                .notes(order.getNotes())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}

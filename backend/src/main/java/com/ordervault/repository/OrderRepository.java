package com.ordervault.repository;

import com.ordervault.entity.Order;
import com.ordervault.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    
    List<Order> findByOrderIdContainingIgnoreCase(String orderId);
    
    List<Order> findByProductNameContainingIgnoreCase(String productName);
    
    List<Order> findByPlatform(String platform);
    
    List<Order> findByStatus(OrderStatus status);
    
    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<Order> findByReturnDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT o FROM Order o WHERE o.status = :status ORDER BY o.orderDate DESC")
    List<Order> findOrdersByStatusOrderByDate(@Param("status") OrderStatus status);
    
    @Query("SELECT o FROM Order o WHERE o.returnDate IS NOT NULL AND o.returnDate >= :date ORDER BY o.returnDate ASC")
    List<Order> findUpcomingReturns(@Param("date") LocalDate date);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    long countByStatus(@Param("status") OrderStatus status);
    
    @Query("SELECT SUM(o.price * o.quantity) FROM Order o WHERE o.orderDate >= :startDate AND o.orderDate <= :endDate")
    Optional<Double> getTotalSpentBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT o FROM Order o WHERE LOWER(o.productName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(o.orderId) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY o.orderDate DESC")
    List<Order> searchOrders(@Param("searchTerm") String searchTerm);
}

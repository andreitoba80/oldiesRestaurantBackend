package com.example.restaurant.oldies.repository;

import com.example.restaurant.oldies.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByOrderId(Long orderId);

    List<Order> findByOrderDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}

package com.example.restaurant.oldies.service;

import com.example.restaurant.oldies.dto.OrderStatusDto;
import com.example.restaurant.oldies.entity.Order;
import org.springframework.stereotype.Component;

@Component
public interface OrderService {
    void createOrder(Order order) throws Exception;

    void updateOrderStatus(Long orderId, OrderStatusDto orderStatusDto) throws Exception;
}

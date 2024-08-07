package com.example.restaurant.oldies.service;

import com.example.restaurant.oldies.dto.OrderStatusDto;
import com.example.restaurant.oldies.entity.Order;
import com.example.restaurant.oldies.exception.NoRightsException;
import com.example.restaurant.oldies.exception.NoSuchEntity;
import com.example.restaurant.oldies.repository.OrderRepository;
import com.example.restaurant.oldies.utils.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) throws Exception {
        if (!LoggedUser.getInstance().getIsAdmin()) {
            try {
                orderRepository.save(order);
            } catch (Exception e) {
                throw new Exception(e.toString());
            }
        } else {
            throw new NoRightsException();
        }
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatusDto orderStatusDto) throws Exception {
        var orderData = orderRepository.findByOrderId(orderId);
        if (orderData != null) {
            orderData.setOrderStatus(orderStatusDto.getOrderStatus());
            orderRepository.save(orderData);
        } else {
            throw new NoSuchEntity("Order");
        }
    }
}

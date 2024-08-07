package com.example.restaurant.oldies.controller;

import com.example.restaurant.oldies.constants.ErrorMessages;
import com.example.restaurant.oldies.dto.DishOrderDto;
import com.example.restaurant.oldies.dto.OrderStatusDto;
import com.example.restaurant.oldies.dto.OrderSubmissionDto;
import com.example.restaurant.oldies.dto.ReportDto;
import com.example.restaurant.oldies.entity.Menu;
import com.example.restaurant.oldies.entity.Order;
import com.example.restaurant.oldies.repository.MenuRepository;
import com.example.restaurant.oldies.service.OrderService;
import com.example.restaurant.oldies.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.restaurant.oldies.utils.Utils.isAdmin;

@RestController
@CrossOrigin
@AllArgsConstructor
public class OrderController implements OrderControllerResource {
    private OrderService orderService;
    private MenuRepository menuRepository;
    private ReportService reportService;

    @Override
    public ResponseEntity<?> createOrder(OrderSubmissionDto orderSubmissionDto) {
        Set<Menu> validDishes = new HashSet<>();
        List<String> skippedDishes = new ArrayList<>();
        float totalCost = 0f;

        for (DishOrderDto dishOrder : orderSubmissionDto.getDishes()) {
            Menu menu = menuRepository.findByUniqueCode(dishOrder.getUniqueCode());
            if (menu != null && menu.getStock() >= dishOrder.getQuantity()) {
                validDishes.add(menu);
                totalCost += menu.getPrice() * dishOrder.getQuantity();
                menu.setStock(menu.getStock() - dishOrder.getQuantity());
                menuRepository.save(menu);
            } else {
                skippedDishes.add(dishOrder.getUniqueCode());
            }
        }

        if (validDishes.isEmpty()) {
            return ResponseEntity.status(400).body("This is an invalid order...");
        }

        Order order = new Order();
        order.setDishes(validDishes);
        order.setTotalCost(totalCost);
        order.setOrderDateTime(LocalDateTime.now());

        try {
            orderService.createOrder(order);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.toString());
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("orderId", order.getOrderId());
        responseBody.put("message", "Order created with available dishes.");

        if (!skippedDishes.isEmpty()) {
            responseBody.put("skippedDishes", skippedDishes);
        }

        return ResponseEntity.status(200).body(responseBody);
    }

    @Override
    public ResponseEntity<String> updateOrderStatus(Long orderId, OrderStatusDto orderStatusDto) {
        if (!isAdmin()) {
            Integer orderStatus = orderStatusDto.getOrderStatus();
            if (orderStatus >= 0 && orderStatus <= 3) {
                try {
                    orderService.updateOrderStatus(orderId, orderStatusDto);
                } catch (Exception e) {
                    ResponseEntity.status(400).body(e.toString());
                }
                return ResponseEntity.status(200).body("Order status have been updated successfully!");
            } else {
                return ResponseEntity.status(400).body("Order status out of bounds. Try one of the following values: 0, 1, 2");
            }
        } else {
            return ResponseEntity.status(400).body(ErrorMessages.NOT_ENOUGH_RIGHTS_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> generateReport(ReportDto reportDto, String format) throws IOException {
        if (!format.equalsIgnoreCase("csv") && !format.equalsIgnoreCase("xml")) {
            return ResponseEntity.badRequest().body("Invalid format type. Please choose 'csv' or 'xml'.");
        }

        Resource report = reportService.generateReport(reportDto.getBeginningReportTime(), reportDto.getEndingReportTime(), format);

        String contentDisposition = "attachment; fileName=report." + format;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(format.equalsIgnoreCase("csv") ? MediaType.TEXT_PLAIN : MediaType.APPLICATION_XML)
                .body(report);
    }
}

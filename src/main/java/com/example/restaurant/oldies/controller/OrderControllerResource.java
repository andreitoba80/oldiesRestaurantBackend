package com.example.restaurant.oldies.controller;

import com.example.restaurant.oldies.dto.OrderStatusDto;
import com.example.restaurant.oldies.dto.OrderSubmissionDto;
import com.example.restaurant.oldies.dto.ReportDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/order")
public interface OrderControllerResource {
    @PostMapping(value = "createOrder")
    ResponseEntity<?> createOrder(@RequestBody OrderSubmissionDto orderSubmissionDto) throws Exception;

    @PutMapping(value = "updateOrderStatus/{orderId}")
    ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatusDto orderStatusDto);

    @PostMapping(value = "generateReport")
    ResponseEntity<?> generateReport(@RequestBody ReportDto reportDto, @RequestParam String format) throws IOException;
}

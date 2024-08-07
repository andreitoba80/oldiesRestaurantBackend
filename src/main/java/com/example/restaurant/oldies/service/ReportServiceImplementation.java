package com.example.restaurant.oldies.service;

import com.example.restaurant.oldies.entity.Menu;
import com.example.restaurant.oldies.entity.Order;
import com.example.restaurant.oldies.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImplementation implements ReportService {
    private OrderRepository orderRepository;

    @Override
    public Resource generateReport(LocalDateTime startDateTime, LocalDateTime endDateTime, String format) throws IOException {
        List<Order> orders = orderRepository.findByOrderDateTimeBetween(startDateTime, endDateTime);

        Map<String, Long> dishCountMap = orders.stream()
                .flatMap(order -> order.getDishes().stream())
                .collect(Collectors.groupingBy(
                        Menu::getDishName,
                        Collectors.counting()
                ));

        String reportContent = format.equalsIgnoreCase("csv") ?
                generateCsvReport(dishCountMap) :
                generateXmlReport(dishCountMap);

        Path tempFile = Files.createTempFile("order-report", "." + format);
        Files.writeString(tempFile, reportContent, StandardCharsets.UTF_8);

        return new UrlResource(tempFile.toUri());
    }

    private String generateCsvReport(Map<String, Long> dishCountMap) {
        StringBuilder csvBuilder = new StringBuilder("Dish Name, Order Count\n");
        dishCountMap.forEach((dishName, count) -> csvBuilder.append(dishName).append(",").append(count).append("\n"));
        return csvBuilder.toString();
    }

    private String generateXmlReport(Map<String, Long> dishCountMap) {
        StringBuilder xmlBuilder = new StringBuilder("<?xml version=\\\"1.0\\\"?>\\n<Report>\\n");
        dishCountMap.forEach((dishName, count) -> {
            xmlBuilder.append("  <Dish>\n")
                    .append("    <Name>").append(dishName).append("</Name>\n")
                    .append("    <Count>").append(count).append("</Count>\n")
                    .append("  </Dish>\n");
        });
        xmlBuilder.append("</Report>\n");
        return xmlBuilder.toString();
    }
}

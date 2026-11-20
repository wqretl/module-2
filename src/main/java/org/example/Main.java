package org.example;

import org.example.OrderSource.HashDelimitedOrderSource;
import org.example.OrderSource.OrderSource;
import org.example.OrderSource.PipeDelimitedOrderSource;
import org.example.entity.Order;
import org.example.service.OrderService;
import org.example.writer.FileOrderResultWriter;
import org.example.writer.OrderResultWriter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        File hashFile = new File("discount_day_without_ext");
        File pipeFile = new File("discount_day.txt");

        OrderSource hashFileSource = new HashDelimitedOrderSource();
        OrderSource pipeFileSource = new PipeDelimitedOrderSource();

        List<Order> hashOrders = hashFileSource.getOrders(hashFile);

        List<Order> pipeOrders = pipeFileSource.getOrders(pipeFile);

        List<Order> allOrders = new ArrayList<>();

        allOrders.addAll(hashOrders);
        allOrders.addAll(pipeOrders);

        OrderService orderService = new OrderService();

        Map<String, BigDecimal> result =
                orderService.processOrders(allOrders);

        File resultFile = new File("orderResult.txt");

        OrderResultWriter writer =
                new FileOrderResultWriter(resultFile);

        writer.write(result);
    }
}
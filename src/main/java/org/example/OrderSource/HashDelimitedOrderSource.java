package org.example.OrderSource;

import org.example.entity.Order;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HashDelimitedOrderSource implements OrderSource {

    @Override
    public List<Order> getOrders(File file) {


        String line;
        List<Order> orders = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            while ((line = bufferedReader.readLine())!=null){
                String[] orderLine = line.split("#", -1);
                LocalDateTime dateTime = LocalDateTime.parse(orderLine[0]);
                String companyName = orderLine[1];
                int amountKg = Integer.parseInt(orderLine[2]);

                Order order = new Order(dateTime, companyName, amountKg);
                orders.add(order);
            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
        return orders;
    }
}

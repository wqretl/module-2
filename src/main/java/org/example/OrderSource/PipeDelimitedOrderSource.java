package org.example.OrderSource;

import org.example.entity.Order;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PipeDelimitedOrderSource implements OrderSource {

    @Override
    public List<Order> getOrders(File file) {
        String line;
        List<Order> orders = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            while ((line = bufferedReader.readLine())!=null){

                String[] lineArray = line.split("\\|",-1);
                LocalDateTime dateTime = LocalDateTime.parse(lineArray[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String companyName = lineArray[1];
                int amountKg = Integer.parseInt(lineArray[2]);

                Order  order = new Order(dateTime,companyName,amountKg);
                orders.add(order);



            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
            return orders;
    }
}

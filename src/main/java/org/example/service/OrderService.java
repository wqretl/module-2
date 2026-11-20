package org.example.service;

import org.example.entity.Order;

import java.math.BigDecimal;
import java.util.*;

public class OrderService {

    private final BigDecimal pricePerKg = new BigDecimal("10.00");
    private final BigDecimal startDiscount= new BigDecimal("0.50");
    private final BigDecimal discountStep= new BigDecimal("0.05");

    public Map<String,BigDecimal> processOrders(List<Order> orders){
        List<Order> orderList= orders.stream().sorted(Comparator.comparing(Order::getOrderDate)).toList();
        Map<String,BigDecimal> map = new HashMap<>();
        for(int i =0;i<orderList.size();i++){
           BigDecimal interimDiscount =
                   discountStep.multiply(BigDecimal.valueOf(i));

           BigDecimal discount =
                   startDiscount.subtract(interimDiscount);

           if (discount.compareTo(BigDecimal.ZERO)<=0){
               discount = BigDecimal.ZERO;
           }


            Order order = orderList.get(i);
           BigDecimal decimalAmountKg = BigDecimal.valueOf(order.getAmountKg());

           BigDecimal baseCost = pricePerKg.multiply(decimalAmountKg);

           BigDecimal discountValue = BigDecimal.ONE.subtract(discount);

           BigDecimal orderCost = baseCost.multiply(discountValue);

           if (!(map.containsKey(order.getCompanyName()))){
               map.put(order.getCompanyName(),orderCost);
           }
           else{
               BigDecimal interimSum = map.get(order.getCompanyName());
               BigDecimal newSum = orderCost.add(interimSum);
               map.put(order.getCompanyName(),newSum);
           }

        }
        return map;

    }
}

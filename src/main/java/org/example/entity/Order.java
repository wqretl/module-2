package org.example.entity;

import java.time.LocalDateTime;

public class Order {
    private LocalDateTime  orderDate;
    private String companyName;
    private int amountKg;

    public Order(LocalDateTime orderDate, String companyName, int amountKg) {
        this.orderDate = orderDate;
        this.companyName = companyName;
        this.amountKg = amountKg;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getAmountKg() {
        return amountKg;
    }
}

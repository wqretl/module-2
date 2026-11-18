package org.example.OrderSource;

import org.example.entity.Order;

import java.io.File;
import java.util.List;

public interface OrderSource  {

    List<Order> getOrders(File file) ;
}

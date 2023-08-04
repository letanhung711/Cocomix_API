package com.example.Library.service;

import com.example.Library.dto.OrderDto;
import com.example.Library.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(Long id);
    String newOrder(OrderDto orderDto);
    List<Order> getAll();
    Optional<Order> getOrderInformation(Long id);
    void deleteOrder(Long orderId);
    void deleteOrderDetail(Long orderDetailId);
    String updateOrder(Long id, OrderDto orderDto);
    String orderConfirm(Long id);
    String orderCancel(Long id);
}

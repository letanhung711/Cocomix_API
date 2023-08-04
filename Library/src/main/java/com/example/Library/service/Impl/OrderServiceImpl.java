package com.example.Library.service.Impl;

import com.example.Library.dto.OrderDto;
import com.example.Library.model.Order;
import com.example.Library.model.OrderDetail;
import com.example.Library.model.OrderStatus;
import com.example.Library.model.Product;
import com.example.Library.repository.OrderDetailRepository;
import com.example.Library.repository.OrderRepository;
import com.example.Library.repository.ProductRepository;
import com.example.Library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public String newOrder(OrderDto orderDto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Product product = productRepository.findByName(orderDto.getTotalproducts());
        if(product == null) {
            return "No product found!";
        }

        Order order = new Order();
        order.setNgaydat(orderDto.getNgaydat());
        order.setName(orderDto.getName());
        order.setAddress(orderDto.getAddress());
        order.setPhoneNumber(orderDto.getPhonenumber());
        order.setNote(orderDto.getNote());
        order.setTotalProducts(orderDto.getTotalproducts());
        order.setProducts(product);
        orderRepository.save(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(Integer.parseInt(orderDto.getQuantity()));
        orderDetail.setPrice(Double.parseDouble(orderDto.getPrice()));
        orderDetail.setTotalprice(Double.parseDouble(orderDto.getTotalprice()));
        orderDetail.setCreate_time(timestamp);
        orderDetail.setOrders(order);
        orderDetail.setProducts(product);
        orderDetailRepository.save(orderDetail);

        return "Create new order successful!";
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void deleteOrderDetail(Long orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }

    @Override
    public String updateOrder(Long id, OrderDto orderDto) {
        Optional<Order> order = orderRepository.findById(id);
        Product product = productRepository.findByName(orderDto.getTotalproducts());
        if(order == null){
            return "Not found order";
        }

        Order order1 = order.get();
        order1.setNgaydat(orderDto.getNgaydat());
        order1.setName(orderDto.getName());
        order1.setAddress(orderDto.getAddress());
        order1.setPhoneNumber(orderDto.getPhonenumber());
        order1.setNote(orderDto.getNote());
        order1.setTotalProducts(orderDto.getTotalproducts());
        order1.setProducts(product);
        orderRepository.save(order1);

        OrderDetail orderDetail = orderDetailRepository.findByOrders_Id(order.get().getId());
        orderDetail.setQuantity(Integer.parseInt(orderDto.getQuantity()));
        orderDetail.setPrice(Double.parseDouble(orderDto.getPrice()));
        orderDetail.setTotalprice(Double.parseDouble(orderDto.getTotalprice()));
        orderDetail.setOrders(order1);
        orderDetail.setProducts(product);
        orderDetailRepository.save(orderDetail);

        return "Update order successful!";
    }

    @Override
    public String orderConfirm(Long id) {
        Optional<Order> orders = orderRepository.findById(id);
        if(orders == null) {
            return "Not found order";
        }
        Order order = orders.get();
        order.setStatus(OrderStatus.CONFIRM);
        orderRepository.save(order);
        return "Order Confirmation";
    }

    @Override
    public String orderCancel(Long id) {
        Optional<Order> orders = orderRepository.findById(id);
        if(orders == null) {
            return "Not found order";
        }
        Order order = orders.get();
        order.setStatus(OrderStatus.CANCEL);
        orderRepository.save(order);
        return "Cancel the order";
    }

    @Override
    public Optional<Order> getOrderInformation(Long id) {
        return orderRepository.findById(id);
    }
}

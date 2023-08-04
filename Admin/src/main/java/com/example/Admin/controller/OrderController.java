package com.example.Admin.controller;

import com.example.Library.dto.OrderDto;
import com.example.Library.model.Order;
import com.example.Library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<Order>> getListOrder(@RequestParam(value = "query", required = false) String keyword) {
        List<Order> order = orderService.getAll();
        if(keyword != null && !keyword.isEmpty()) {
            order = order.stream().filter(order1 ->
                    order1.getName().contains(keyword)).collect(Collectors.toList());
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Optional<Order>> getOrder(@PathVariable("orderId") Long orderId) {
        Optional<Order> order = orderService.getOrderInformation(orderId);
        if(order == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> newOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.newOrder(orderDto));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId) {
        Optional<Order> order = orderService.findById(orderId);
        return order.map(order1 -> {
            orderService.deleteOrderDetail(orderId);
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok("Delete order successful!");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Delete order fail!"));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable("orderId") Long orderId,
                                              @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, orderDto));
    }

    @PutMapping("/{orderId}/confirm")
    public ResponseEntity<String> orderConfirmation(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.orderConfirm(orderId));
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> orderCancel(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.orderCancel(orderId));
    }
}

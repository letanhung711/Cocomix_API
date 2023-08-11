package com.example.Library.repository;

import com.example.Library.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    OrderDetail findByOrders_Id(Long id);
    List<OrderDetail> findByUsers_Id(Long id);
}

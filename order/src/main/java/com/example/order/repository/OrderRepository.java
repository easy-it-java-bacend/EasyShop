package com.example.order.repository;

import com.example.order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT rd.customer_id FROM tb_order as rd WHERE rd.id = ?1", nativeQuery = true)
    Long getCustomerId(Long id);
}

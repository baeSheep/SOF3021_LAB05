package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
    // Các phương thức tùy chỉnh khác (nếu có)
}

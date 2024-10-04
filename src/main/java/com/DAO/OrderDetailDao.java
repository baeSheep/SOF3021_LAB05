package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long>{

}

package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

}
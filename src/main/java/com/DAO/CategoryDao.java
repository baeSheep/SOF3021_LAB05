package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, String>{

}

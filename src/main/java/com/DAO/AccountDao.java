package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Account;

public interface AccountDao extends JpaRepository<Account, String>{

}

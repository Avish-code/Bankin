package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.model.Customer;

public interface BankRepo extends JpaRepository<Customer,Long> {
	
}

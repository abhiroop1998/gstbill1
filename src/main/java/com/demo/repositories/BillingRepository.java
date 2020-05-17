package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entities.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long>{
	
	@Query("select u from Billing u where u.bcode = :code")
	Billing findByCode(Long code);
	
}

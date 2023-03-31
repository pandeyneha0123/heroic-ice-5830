package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSessionDao extends JpaRepository<CustomerSession, Integer> {
	public CustomerSession findByUniqueId(String customerId);
}
package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Benificiary;

public interface BenificiaryRepository extends JpaRepository<Benificiary, Integer> {

}

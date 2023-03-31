package com.masai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Transection;
import com.masai.model.Wallet;

@Repository
public interface TransactionDao extends JpaRepository<Transection, Integer> {
	
//	@Query("select wal.transections Wallet wal where wal.walletId =?1")
//	public List<Transection> findByWalletId(Integer id);
	
	@Query("select t from Transection t where t.transactionDate >= ?1 and t.transactionDate <= ?2")
	public List<Transection> findTransactionByDate(LocalDate ld1, LocalDate ld2);
	
	public List<Transection> findByTransectionType(String type);
}

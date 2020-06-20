package com.onlineBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineBank.domain.model.PrimaryAccount;


@Repository
public interface PrimaryAccountRepository extends JpaRepository<PrimaryAccount, Long> {
	
	PrimaryAccount findByAccountNumber(int accountNumber);
}

package com.onlineBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineBank.domain.model.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
	
	SavingsAccount findByAccountNumber(int accountNumber);

}

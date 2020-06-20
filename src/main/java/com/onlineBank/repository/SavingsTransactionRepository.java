package com.onlineBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineBank.domain.model.SavingsTransaction;

@Repository
public interface SavingsTransactionRepository extends JpaRepository<SavingsTransaction, Long> {

}

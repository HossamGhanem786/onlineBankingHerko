package com.onlineBank.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlineBank.domain.model.PrimaryTransaction;

@Repository
public interface PrimaryTransactionRepository extends JpaRepository<PrimaryTransaction, Long> {

	
	@Query("SELECT pt FROM PrimaryTransaction pt WHERE primary_account_id = :id ")
	List<PrimaryTransaction> viewPrimaryTransactionList(@Param("id") Long primaryAccountId);
	
	
//	@Query("SELECT r FROM Review r WHERE course_id = :id ")
//	List<Review> viewReviewsByCourseId(@Param("id") Long courseRId);
	
}

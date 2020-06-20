package com.onlineBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineBank.domain.model.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {
	
	Recipient findByName(String name);
	void deleteByName(String name);

}

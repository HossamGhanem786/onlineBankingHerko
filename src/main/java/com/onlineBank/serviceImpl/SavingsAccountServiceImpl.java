package com.onlineBank.serviceImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineBank.domain.dto.SavingsAccountDTO;
import com.onlineBank.domain.model.SavingsAccount;
import com.onlineBank.service.SavingsAccountService;



@Service
@Transactional
public class SavingsAccountServiceImpl extends BaseAbstractService<SavingsAccount> implements SavingsAccountService{
	
	public SavingsAccountServiceImpl(JpaRepository<SavingsAccount, Long> jpaRepository) {
		super(jpaRepository);
	}


	public SavingsAccountDTO saveSavingsAccount(SavingsAccountDTO savingsAccountDTO) throws Exception {
		SavingsAccount savingsAccount = this.save(modelMapper.map(savingsAccountDTO, SavingsAccount.class));
		return modelMapper.map(savingsAccount, SavingsAccountDTO.class);
		
	}
		
	
	
	
	
	
	
	
		}

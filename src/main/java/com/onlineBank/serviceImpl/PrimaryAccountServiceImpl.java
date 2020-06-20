package com.onlineBank.serviceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineBank.domain.dto.PrimaryAccountDTO;
import com.onlineBank.domain.model.PrimaryAccount;
import com.onlineBank.service.PrimaryAccountService;


@Service
@Transactional
public class PrimaryAccountServiceImpl extends BaseAbstractService<PrimaryAccount> implements PrimaryAccountService{
	
	public PrimaryAccountServiceImpl(JpaRepository<PrimaryAccount, Long> jpaRepository) {
		super(jpaRepository);
	}


	public PrimaryAccountDTO savePrimaryAccount(PrimaryAccountDTO primaryAccountDTO) throws Exception {
		PrimaryAccount primaryAccount = this.save(modelMapper.map(primaryAccountDTO, PrimaryAccount.class));
		return modelMapper.map(primaryAccount, PrimaryAccountDTO.class);
		
	}
		
	
	
	
	
	
	
	
		}

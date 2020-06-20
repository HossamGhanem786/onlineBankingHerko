package com.onlineBank.service;

import java.util.List;

import com.onlineBank.domain.dto.AppointmentDTOO;
import com.onlineBank.domain.dto.PrimaryAccountDTO;
import com.onlineBank.domain.dto.PrimaryTransactionDTOO;
import com.onlineBank.domain.dto.RecipientDTOO;
import com.onlineBank.domain.dto.ResponseDTO;
import com.onlineBank.domain.dto.SavingsAccountDTO;
import com.onlineBank.domain.dto.SavingsTransactionDTOO;

public interface AccountService {
	List<PrimaryTransactionDTOO> findPrimaryTransactionList(String username) throws Exception;
	List<SavingsTransactionDTOO> findSavingsTransactionList(String username) throws Exception;
	List<AppointmentDTOO> getAppointmentList(String username);
	PrimaryAccountDTO createPrimaryAccount() throws Exception;
	SavingsAccountDTO createSavingsAccount() throws Exception;
	ResponseDTO  depositt(String accountType,Double amount,String username) throws Exception;
	ResponseDTO withdraww(String accountType, Double amount, String username) throws Exception;
	ResponseDTO transferBetweenAccounts(String transferFrom,String transferTo, Double amount, String username) throws Exception;
	ResponseDTO transferToSomeone(String recipientName, String accountType, double amount, String username) throws Exception ;

}

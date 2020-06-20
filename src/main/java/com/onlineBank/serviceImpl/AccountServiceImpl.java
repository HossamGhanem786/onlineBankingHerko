package com.onlineBank.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineBank.domain.dto.AppointmentDTOO;
import com.onlineBank.domain.dto.PrimaryAccountDTO;
import com.onlineBank.domain.dto.PrimaryTransactionDTOO;
import com.onlineBank.domain.dto.RecipientDTOO;
import com.onlineBank.domain.dto.ResponseDTO;
import com.onlineBank.domain.dto.SavingsAccountDTO;
import com.onlineBank.domain.dto.SavingsTransactionDTOO;
import com.onlineBank.domain.dto.UserDTO;
import com.onlineBank.service.AccountService;
import com.onlineBank.service.PrimaryAccountService;
import com.onlineBank.service.RecipientService;
import com.onlineBank.service.SavingsAccountService;
import com.onlineBank.service.UserService;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private PrimaryAccountService primaryAccountService;
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	@Autowired
	private RecipientService recipientService;
	
	private static int nextAccountNumber=11223145;

	
	
public List<PrimaryTransactionDTOO> findPrimaryTransactionList(String username) throws Exception{
		
		UserDTO userDTO= userService.findByUsername(username);
	 	PrimaryAccountDTO primaryAccountDTO = userDTO.getPrimaryAccount();
	 	return new ArrayList<PrimaryTransactionDTOO>(primaryAccountDTO.getPrimaryTransactionList()) ;
	}

public List<SavingsTransactionDTOO> findSavingsTransactionList(String username) throws Exception{
	UserDTO userDTO= userService.findByUsername(username);
	SavingsAccountDTO savingsAccountDTO = userDTO.getSavingsAccount();
 	return new ArrayList<SavingsTransactionDTOO>(savingsAccountDTO.getSavingsTransactionList());
 	
}


public List<AppointmentDTOO> getAppointmentList(String username){
	UserDTO userDTO= userService.findByUsername(username);
	List<AppointmentDTOO> appointmentList=	new ArrayList<AppointmentDTOO>(userDTO.getAppointmentList());
return appointmentList;

}
public 	PrimaryAccountDTO createPrimaryAccount() throws Exception {
	
	PrimaryAccountDTO primaryAccountDTO= new PrimaryAccountDTO();
	primaryAccountDTO.setAccountBalance(new BigDecimal(0.0));
	primaryAccountDTO.setAccountNumber(accountGen());
	return  primaryAccountService.savePrimaryAccount(primaryAccountDTO);

	}

	public SavingsAccountDTO createSavingsAccount() throws Exception {
	
		SavingsAccountDTO savingsAccountDTO= new SavingsAccountDTO();
		savingsAccountDTO.setAccountBalance(new BigDecimal(0.0));
		savingsAccountDTO.setAccountNumber(accountGen());
		 return savingsAccountService.saveSavingsAccount(savingsAccountDTO);
		
	}
	
	public ResponseDTO  depositt(String accountType,Double amount,String username) throws Exception {
		UserDTO userDTO = userService.findByUsername(username);
		
		if(accountType.equalsIgnoreCase("Primary")) {
			
			PrimaryAccountDTO primaryAccountDTO = userDTO.getPrimaryAccount();
			primaryAccountDTO.setAccountBalance(primaryAccountDTO.getAccountBalance().add(new BigDecimal(amount)));
			
			Date date= new Date();
			PrimaryTransactionDTOO primaryTransactionDTOO = new PrimaryTransactionDTOO();				
			primaryTransactionDTOO.setDescription("Deposit To Primary Account");
			primaryTransactionDTOO.setType("Account");
			primaryTransactionDTOO.setStatus("Finished");
			primaryTransactionDTOO.setAmount(amount);
			primaryTransactionDTOO.setAvailableBalance(primaryAccountDTO.getAccountBalance());
			primaryTransactionDTOO.setDate(date);
			primaryAccountDTO.getPrimaryTransactionList().add(primaryTransactionDTOO);
			primaryAccountService.savePrimaryAccount(primaryAccountDTO);
			
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setStatus("Deposit Succesfully to PrimaryAcccount !!");
			responseDTO.setBody("You have deposit PrimaryAcccount");
			return responseDTO;
		
		}else if(accountType.equalsIgnoreCase("Savings")) {
			
			SavingsAccountDTO savingsAccountDTO = userDTO.getSavingsAccount();
			savingsAccountDTO.setAccountBalance(savingsAccountDTO.getAccountBalance().add(new BigDecimal(amount)));
		
			Date date= new Date();
			SavingsTransactionDTOO savingsTransactionDTOO = new SavingsTransactionDTOO();
			savingsTransactionDTOO.setDescription("Deposit To Savings Account");
			savingsTransactionDTOO.setType("Account");
			savingsTransactionDTOO.setStatus("Finished");
			savingsTransactionDTOO.setAmount(amount);
			savingsTransactionDTOO.setAvailableBalance(savingsAccountDTO.getAccountBalance());
			savingsTransactionDTOO.setDate(date);
			savingsAccountDTO.getSavingsTransactionList().add(savingsTransactionDTOO);
			savingsAccountService.saveSavingsAccount(savingsAccountDTO);
			
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setStatus("Deposit Succesfully to SavingSAcccount ! !!");
			responseDTO.setBody("You have deposit SavingSAcccount ");
			return responseDTO;
			
		}else {
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setStatus("Deposit failed to SavingSAcccount ! !!");
			responseDTO.setBody("You have failed deposit SavingSAcccount ");
			return responseDTO;
		
		}
	}

public ResponseDTO withdraww(String accountType, Double amount, String username) throws Exception {
		
	UserDTO userDTO =  userService.findByUsername(username);
	
	if(accountType.equalsIgnoreCase("Primary") && amount<= userDTO.getPrimaryAccount().getAccountBalance().doubleValue()) {
		PrimaryAccountDTO primaryAccountDTO = userDTO.getPrimaryAccount();
		primaryAccountDTO.setAccountBalance(primaryAccountDTO.getAccountBalance().subtract(new BigDecimal(amount)));
		
		
		
		Date date= new Date();
		PrimaryTransactionDTOO primaryTransactionDTOO = new PrimaryTransactionDTOO();
		primaryTransactionDTOO.setDescription("Withraw To Primary Account");
		primaryTransactionDTOO.setType("Account");
		primaryTransactionDTOO.setStatus("Finished");
		primaryTransactionDTOO.setAmount(amount);
		primaryTransactionDTOO.setAvailableBalance(primaryAccountDTO.getAccountBalance());
		primaryTransactionDTOO.setDate(date);
		primaryAccountDTO.getPrimaryTransactionList().add(primaryTransactionDTOO);
		primaryAccountService.savePrimaryAccount(primaryAccountDTO);
		
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus("Withdraw Succesfully to PrimaryAcccount !!");
		responseDTO.setBody("You have withdraw PrimaryAcccount");
		return responseDTO;
		
	}else if(accountType.equalsIgnoreCase("Savings") && amount<= userDTO.getSavingsAccount().getAccountBalance().doubleValue()) {
		
		SavingsAccountDTO savingsAccountDTO = userDTO.getSavingsAccount();
		savingsAccountDTO.setAccountBalance(savingsAccountDTO.getAccountBalance().subtract(new BigDecimal(amount)));
		
		Date date= new Date();
		SavingsTransactionDTOO savingsTransactionDTOO = new SavingsTransactionDTOO();
		savingsTransactionDTOO.setDescription("Withdraw To Savings Account");
		savingsTransactionDTOO.setType("Account");
		savingsTransactionDTOO.setStatus("Finished");
		savingsTransactionDTOO.setAmount(amount);
		savingsTransactionDTOO.setAvailableBalance(savingsAccountDTO.getAccountBalance());
		savingsTransactionDTOO.setDate(date);
		savingsAccountDTO.getSavingsTransactionList().add(savingsTransactionDTOO);
		savingsAccountService.saveSavingsAccount(savingsAccountDTO);
		
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus("Withdraw Succesfully to SavingsAcccount !!");
		responseDTO.setBody("You have withdraw SavingsAcccount");
		return responseDTO;
	}else {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus("Try again,problem happened !");
		responseDTO.setBody("Try again,problem happened !");
		return responseDTO;
	}
}

public ResponseDTO transferBetweenAccounts(String transferFrom,
		String transferTo, Double amount, String username) throws Exception {
	UserDTO userDTO =  userService.findByUsername(username);
	PrimaryAccountDTO primaryAccountDTO = userDTO.getPrimaryAccount();
	SavingsAccountDTO savingsAccountDTO = userDTO.getSavingsAccount();
	ResponseDTO responseDTO = new ResponseDTO();
	Date date= new Date();
	if(transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")
			&& primaryAccountDTO.getAccountBalance().doubleValue()>= amount) {
		primaryAccountDTO.setAccountBalance(primaryAccountDTO.getAccountBalance().subtract(new BigDecimal(amount)));
		savingsAccountDTO.setAccountBalance(savingsAccountDTO.getAccountBalance().add(new BigDecimal(amount)));
		
		
		PrimaryTransactionDTOO primaryTransactionDTOO = new PrimaryTransactionDTOO();				
		primaryTransactionDTOO.setDescription("Transfer To Savings Account");
		primaryTransactionDTOO.setType("Transfer");
		primaryTransactionDTOO.setStatus("Finished");
		primaryTransactionDTOO.setAmount(amount);
		primaryTransactionDTOO.setAvailableBalance(primaryAccountDTO.getAccountBalance());
		primaryTransactionDTOO.setDate(date);
		primaryAccountDTO.getPrimaryTransactionList().add(primaryTransactionDTOO);
		primaryAccountService.savePrimaryAccount(primaryAccountDTO);
		
		SavingsTransactionDTOO savingsTransactionDTOO = new SavingsTransactionDTOO();
		savingsTransactionDTOO.setDescription("Transfer From  primary Account");
		savingsTransactionDTOO.setType("Transfer");
		savingsTransactionDTOO.setStatus("Finished");
		savingsTransactionDTOO.setAmount(amount);
		savingsTransactionDTOO.setAvailableBalance(savingsAccountDTO.getAccountBalance());
		savingsTransactionDTOO.setDate(date);
		savingsAccountDTO.getSavingsTransactionList().add(savingsTransactionDTOO);
		savingsAccountService.saveSavingsAccount(savingsAccountDTO);
		
		
		responseDTO.setStatus("Transfer From Primary Account to SavingSAcccount Succesfully ! !!");
		responseDTO.setBody("You have Transfer from Primary !!");
		return responseDTO;
		
		
	}else if(transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")
			&& savingsAccountDTO.getAccountBalance().doubleValue()>= amount) {
		savingsAccountDTO.setAccountBalance(savingsAccountDTO.getAccountBalance().subtract(new BigDecimal(amount)));
		primaryAccountDTO.setAccountBalance(primaryAccountDTO.getAccountBalance().add(new BigDecimal(amount)));
		
		
		
		SavingsTransactionDTOO savingsTransactionDTOO = new SavingsTransactionDTOO();
		savingsTransactionDTOO.setDescription("Transfer TO  Primary Account");
		savingsTransactionDTOO.setType("Transfer");
		savingsTransactionDTOO.setStatus("Finished");
		savingsTransactionDTOO.setAmount(amount);
		savingsTransactionDTOO.setAvailableBalance(savingsAccountDTO.getAccountBalance());
		savingsTransactionDTOO.setDate(date);
		savingsAccountDTO.getSavingsTransactionList().add(savingsTransactionDTOO);
		savingsAccountService.saveSavingsAccount(savingsAccountDTO);
		
		PrimaryTransactionDTOO primaryTransactionDTOO = new PrimaryTransactionDTOO();				
		primaryTransactionDTOO.setDescription("Transfer From Savings Account");
		primaryTransactionDTOO.setType("Transfer");
		primaryTransactionDTOO.setStatus("Finished");
		primaryTransactionDTOO.setAmount(amount);
		primaryTransactionDTOO.setAvailableBalance(primaryAccountDTO.getAccountBalance());
		primaryTransactionDTOO.setDate(date);
		primaryAccountDTO.getPrimaryTransactionList().add(primaryTransactionDTOO);
		primaryAccountService.savePrimaryAccount(primaryAccountDTO);
		
		responseDTO.setStatus("Transfer From SavingsAccount to PrimaryAcccount Succesfully ! !!");
		responseDTO.setBody("You have Transfer from Savings !! ");
		return responseDTO;
	}else {
		responseDTO.setStatus(" Failed To Transfer... ! !!");
		responseDTO.setBody("Falied To Transfer.... !! ");
		return responseDTO;
	}
}



	private int accountGen() {
		
		return ++nextAccountNumber;
	}

	public ResponseDTO transferToSomeone(String recipientName, String accountType, double amount, String username) throws Exception {
		UserDTO userDTO = userService.findByUsername(username);
		PrimaryAccountDTO primaryAccountDTO = userDTO.getPrimaryAccount();
		SavingsAccountDTO savingsAccountDTO = userDTO.getSavingsAccount();
		ResponseDTO responseDTO = new ResponseDTO();
		Date date= new Date();
		
	    
	    
	    if(accountType.equalsIgnoreCase("Primary") && amount<= userDTO.getPrimaryAccount().getAccountBalance().doubleValue()) {
	    	
	    	RecipientDTOO recipientDTOO=recipientService.findRecipientByName(recipientName);
		    recipientDTOO.setAmount(amount);
		    recipientDTOO.setDescription("From "+userDTO.getFirstName()+" "+ userDTO.getLastName());
		    recipientService.saveRecipient(recipientDTOO, username);
		    
			primaryAccountDTO.setAccountBalance(primaryAccountDTO.getAccountBalance().subtract(new BigDecimal(amount)));
			
			PrimaryTransactionDTOO primaryTransactionDTOO = new PrimaryTransactionDTOO();
			primaryTransactionDTOO.setDescription("Transfer to "+recipientName);
			primaryTransactionDTOO.setType("Transfer");
			primaryTransactionDTOO.setStatus("Finished");
			primaryTransactionDTOO.setAmount(amount);
			primaryTransactionDTOO.setAvailableBalance(primaryAccountDTO.getAccountBalance());
			primaryTransactionDTOO.setDate(date);
			primaryAccountDTO.getPrimaryTransactionList().add(primaryTransactionDTOO);
			primaryAccountService.savePrimaryAccount(primaryAccountDTO);
			
			responseDTO.setStatus("Success!");
			responseDTO.setBody("Transfer  Succesfully to "+recipientName+" !!");
			return responseDTO;
	    }else if(accountType.equalsIgnoreCase("Savings") && amount<= userDTO.getSavingsAccount().getAccountBalance().doubleValue()) {
			
	    	RecipientDTOO recipientDTOO=recipientService.findRecipientByName(recipientName);
		    recipientDTOO.setAmount(amount);
		    recipientDTOO.setDescription("From "+userDTO.getFirstName()+" "+ userDTO.getLastName());
		    recipientService.saveRecipient(recipientDTOO, username);
	    	
			savingsAccountDTO.setAccountBalance(savingsAccountDTO.getAccountBalance().subtract(new BigDecimal(amount)));
			
			SavingsTransactionDTOO savingsTransactionDTOO = new SavingsTransactionDTOO();
			savingsTransactionDTOO.setDescription("Transfer to "+recipientName);
			savingsTransactionDTOO.setType("Transfer");
			savingsTransactionDTOO.setStatus("Finished");
			savingsTransactionDTOO.setAmount(amount);
			savingsTransactionDTOO.setAvailableBalance(savingsAccountDTO.getAccountBalance());
			savingsTransactionDTOO.setDate(date);
			savingsAccountDTO.getSavingsTransactionList().add(savingsTransactionDTOO);
			savingsAccountService.saveSavingsAccount(savingsAccountDTO);
			
			responseDTO.setStatus("Success!");
			responseDTO.setBody("Transfer  Succesfully to "+recipientName+" !!");
			return responseDTO;
		}else {
			responseDTO.setStatus("Try again,problem happened !");
			responseDTO.setBody("Try again,problem happened !");
			return responseDTO;
		}
	}

	

	

	
	
   
   
   
   
   
   
   
   
}

package com.onlineBank.controller;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBank.domain.dto.PrimaryTransactionDTOO;
import com.onlineBank.domain.dto.ResponseDTO;
import com.onlineBank.domain.dto.SavingsTransactionDTOO;
import com.onlineBank.domain.dto.ToSomeOneElseDTO;
import com.onlineBank.domain.dto.TranferBetweenDTO;
import com.onlineBank.domain.dto.TransactionDTO;
import com.onlineBank.domain.dto.UserDTO;
import com.onlineBank.service.AccountService;
import com.onlineBank.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/account")
@Api(value = "AccountController", produces = "application/json")
@CrossOrigin(origins ="http://localhost:4200")
public class AccountController {

	@Autowired
	private UserService  userService;
	
	@Autowired
	AccountService  accountService;
	
	@RequestMapping(value = "/primaryAccount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  BigDecimal userFrontPrimary() throws Exception{
		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		UserDTO userDTO =  userService.findByUsername(username);
		return userDTO.getPrimaryAccount().getAccountBalance();
	}
	
	
	
	@RequestMapping(value = "/primaryAccountNumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  Integer userFrontPrimaryNumber() throws Exception{
		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		UserDTO userDTO =  userService.findByUsername(username);
		return userDTO.getPrimaryAccount().getAccountNumber();
	}
	
	@RequestMapping(value = "/savingsAccount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  BigDecimal userFrontSavings() throws Exception{
		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		UserDTO userDTO =  userService.findByUsername(username);
		return userDTO.getSavingsAccount().getAccountBalance();
	}
	
	@RequestMapping(value = "/savingsAccountNumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  Integer userFrontSavingsNumber() throws Exception{
		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		UserDTO userDTO =  userService.findByUsername(username);
		return userDTO.getSavingsAccount().getAccountNumber();
	}
	@RequestMapping(value = "/primaryTransactionList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Collection<PrimaryTransactionDTOO> primaryTransactionList() throws Exception{
		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		return accountService.findPrimaryTransactionList(username);
	
	}
	
	@RequestMapping(value = "/savingsTransactionList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  Collection<SavingsTransactionDTOO> savingsTransactionList() throws Exception{
		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		
		return accountService.findSavingsTransactionList(username);
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseDTO deposit(@RequestBody(required = true) TransactionDTO depositDTO) throws Exception {
		System.out.println("DepositDTO is " +  depositDTO);
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return accountService.depositt(depositDTO.getAccountType(), depositDTO.getAmount(), username);
    }
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseDTO withdraww(@RequestBody(required = true) TransactionDTO withdrawDTO) throws Exception {
		System.out.println("WithdrawDTO is " +  withdrawDTO);
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return accountService.withdraww(withdrawDTO.getAccountType(),  withdrawDTO.getAmount(), username);
    }
	
	
	@RequestMapping(value = "/transferBetween", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseDTO transferBetween(@RequestBody(required = true) TranferBetweenDTO tranferBetweenDTO) throws Exception {
		System.out.println("DepositDTO is transfer " +  tranferBetweenDTO);
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return accountService.transferBetweenAccounts(tranferBetweenDTO
        		.getTransferFrom(), tranferBetweenDTO.getTransferTo(), tranferBetweenDTO.getAmount(),username);
    }
	
	
	
	
	

	@RequestMapping(value = "/transfer/toSomeoneElse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseDTO toSomeoneElse(@RequestBody(required = true) ToSomeOneElseDTO toSomeOneElseDTO) throws Exception{
		System.out.println("DepositDTO is transfer " +  toSomeOneElseDTO);
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("Username is 11 " + username);
        return accountService.transferToSomeone(toSomeOneElseDTO
        		.getRecipientName(), toSomeOneElseDTO.getAccountType(), toSomeOneElseDTO.getAmount(),username);
	}
	
	 

}

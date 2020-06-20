package com.onlineBank.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBank.domain.dto.AppointmentDTO;
import com.onlineBank.domain.dto.PrimaryTransactionDTOO;
import com.onlineBank.domain.dto.ResponseDTO;
import com.onlineBank.domain.dto.RoleDTO;
import com.onlineBank.domain.dto.SavingsTransactionDTOO;
import com.onlineBank.domain.dto.UserDTO;
import com.onlineBank.service.AccountService;
import com.onlineBank.service.AppointmentService;
import com.onlineBank.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/admin")
@Api(value = "AdminController", produces = "application/json")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins ="http://localhost:4200")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	AccountService  accountService;
	@Autowired
	AppointmentService  appointmentService;
	
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<UserDTO> findAllUsers() throws Exception {
		return userService.findAllUsers();
	}
	
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody RoleDTO saveRole(@RequestBody(required = true) RoleDTO roleDTO) throws Exception {
		return userService.saveRole(roleDTO);

	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<RoleDTO> viewRoles() throws Exception {
		return userService.viewRoles();

	}
	
	@RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody RoleDTO viewRole(@PathVariable Long roleId) throws Exception {
		return userService.viewRole(roleId);
	}
	
	@RequestMapping(value = "/activated/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> activatedUser(@PathVariable("username") String username) throws Exception {
		return userService.activatedUser(username);
	}
	
	@RequestMapping(value = "/deActivated/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> deActivatedUser(@PathVariable("username") String username) throws Exception {
		return userService.deActivatedUser(username);
	}
	
	@RequestMapping(value = "/primaryTransaction/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Collection<PrimaryTransactionDTOO> primaryTransactionList(
			@PathVariable("username") String username) throws Exception{
		return accountService.findPrimaryTransactionList(username);

}
	@RequestMapping(value = "/savingsTransaction/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Collection<SavingsTransactionDTOO> savingsTransactionList(
			@PathVariable("username") String username) throws Exception{
				return accountService.findSavingsTransactionList(username);

}
	
	@RequestMapping(value = "/primaryAccount/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  BigDecimal userFrontPrimary(@PathVariable("username") String username) throws Exception{
		
		UserDTO userDTO =  userService.findByUsername(username);
		return userDTO.getPrimaryAccount().getAccountBalance();
	}
	
	@RequestMapping(value = "/savingsAccount/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  BigDecimal userFrontSavings(@PathVariable("username") String username) throws Exception{
		
		UserDTO userDTO =  userService.findByUsername(username);
		return userDTO.getSavingsAccount().getAccountBalance();
	}
	
	@RequestMapping(value = "/appointments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<AppointmentDTO> findAllAppointments() throws Exception {
		return appointmentService.findAllAppointments();
	} 
	
	@RequestMapping(value = "/appointment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  ResponseDTO confirmAppointment(@PathVariable("id") Long id) throws Exception{
		 return appointmentService.confirmAppointment(id);
	}
	
	
}

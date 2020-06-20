package com.onlineBank.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBank.domain.dto.RecipientDTOO;
import com.onlineBank.domain.dto.ResponseDTO;
import com.onlineBank.service.RecipientService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/recipient")
@Api(value = "RecipientController", produces = "application/json")
@CrossOrigin(origins ="http://localhost:4200")
public class RecipientController {
	
	@Autowired
	RecipientService recipientService ;
	
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseDTO saveRecipient(@RequestBody(required = true) RecipientDTOO recipientDTOO) throws Exception {
		
		System.out.println("RecipientDTO is " +  recipientDTOO);
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		
		 return recipientService.saveRecipient(recipientDTOO,username);
	}
	
	@RequestMapping(value = "/edit/{recipientName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody RecipientDTOO editRecipient(@PathVariable("recipientName") String recipientName) {
		return recipientService.findRecipientByName(recipientName);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseDTO deleteRecipient(@RequestBody(required = true)String recipientName) {
		return recipientService.deleteRecipientByName(recipientName);
	}
	
	@RequestMapping(value = "/recipientList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  Collection<RecipientDTOO> recipientList() throws Exception{
		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		
		return recipientService.getRecipientList(username);
	}

}

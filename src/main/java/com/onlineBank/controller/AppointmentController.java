package com.onlineBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBank.domain.dto.AppointmentDTOO;
import com.onlineBank.domain.dto.ResponseDTO;
import com.onlineBank.service.AppointmentService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/appointment")
@Api(value = "AppointmentController", produces = "application/json")
@CrossOrigin(origins ="http://localhost:4200")
public class AppointmentController {
	
	@Autowired
	AppointmentService  appointmentService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  @ResponseBody ResponseDTO saveAppointent(@RequestBody(required = true) AppointmentDTOO appointmentDTOO) throws Exception {
		System.out.println("AppointmentDTOO "+ appointmentDTOO);
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		 appointmentService.createAppointment(appointmentDTOO, username);
		 ResponseDTO responseDTO = new ResponseDTO();
		 responseDTO.setBody("Appointment save Successfully!!");
		 responseDTO.setStatus("Sucess");
		 return responseDTO;
		 
	}

}

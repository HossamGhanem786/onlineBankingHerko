package com.onlineBank.service;

import java.util.List;

import com.onlineBank.domain.dto.AppointmentDTO;
import com.onlineBank.domain.dto.AppointmentDTOO;
import com.onlineBank.domain.dto.PrimaryTransactionDTOO;
import com.onlineBank.domain.dto.ResponseDTO;

public interface AppointmentService {
	
     void createAppointment(AppointmentDTOO appointmentDTOO , String username ) throws Exception;
	AppointmentDTOO findAppointmentById(Long id);
	ResponseDTO confirmAppointment(Long id) throws Exception;
	List<AppointmentDTO> findAllAppointments() throws Exception;
}

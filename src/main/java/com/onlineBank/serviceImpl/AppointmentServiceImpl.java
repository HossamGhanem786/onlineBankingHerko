package com.onlineBank.serviceImpl;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.onlineBank.domain.dto.AppointmentDTO;
import com.onlineBank.domain.dto.AppointmentDTOO;
import com.onlineBank.domain.dto.ResponseDTO;
import com.onlineBank.domain.dto.UserDTO;
import com.onlineBank.domain.model.Appointment;
import com.onlineBank.domain.model.User;
import com.onlineBank.repository.AppointmenttRepository;
import com.onlineBank.service.AppointmentService;
import com.onlineBank.service.UserService;

@Service
@Transactional
public class AppointmentServiceImpl extends BaseAbstractService<Appointment> implements AppointmentService{	
@Autowired
AppointmenttRepository appointmenttRepository;

@Autowired
UserService  userService;

	public AppointmentServiceImpl(JpaRepository<Appointment, Long> jpaRepository) {
		super(jpaRepository);
	}

	public void createAppointment(AppointmentDTOO appointmentDTOO , String username ) throws Exception {
		Appointment appointment =modelMapper.map(appointmentDTOO, Appointment.class);
		UserDTO userDTO = userService.findByUsername(username);
		User user= this.modelMapper.map(userDTO, User.class);
		appointment.setDate(appointmentDTOO.getDate());
		appointment.setConfirmed(false);
		appointment.setUser(user);
		appointment=save(appointment);
	}
	
    public AppointmentDTOO findAppointmentById(Long id) {
		
		Appointment appointment = appointmenttRepository.findById(id).get();
		System.out.println("appointment "+appointment);
		if (appointment == null) {
			System.out.println("Appointment Not found ");
		}
			return modelMapper.map(appointment, AppointmentDTOO.class);
		}
    
    public ResponseDTO confirmAppointment(Long id) throws Exception {
    	ResponseDTO responseDTO = new ResponseDTO();
    	Appointment appointment = appointmenttRepository.findById(id).get();
    	if(appointment != null) {
    	appointment.setConfirmed(true);
    	save(appointment);
    	
		responseDTO.setStatus("Success");
		responseDTO.setBody("Appointment confirmed Succesfully  ! !!");
		return responseDTO;
    	
    	}
    	responseDTO.setStatus("Failed");
		responseDTO.setBody("Failed to confirm!!");
		return responseDTO;
    	
    }
	
    public List<AppointmentDTO> findAllAppointments() throws Exception {
		return this.findAll().stream().map(appointment -> modelMapper.map(appointment, AppointmentDTO.class)).collect(Collectors.toList());
	}

	
	
	
	
	
	
}

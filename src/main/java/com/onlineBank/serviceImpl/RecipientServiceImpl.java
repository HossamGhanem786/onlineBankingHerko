package com.onlineBank.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlineBank.domain.dto.RecipientDTOO;
import com.onlineBank.domain.dto.ResponseDTO;
import com.onlineBank.domain.dto.UserDTO;
import com.onlineBank.domain.model.Recipient;
import com.onlineBank.domain.model.User;
import com.onlineBank.repository.RecipientRepository;
import com.onlineBank.service.RecipientService;
import com.onlineBank.service.UserService;

@Service
@Transactional
public class RecipientServiceImpl extends BaseAbstractService<Recipient> implements RecipientService {

	@Autowired
	private  RecipientRepository recipientRepository;
	
	@Autowired
	private UserService userService;
	
	
	public RecipientServiceImpl(JpaRepository<Recipient, Long> jpaRepository) {
		super(jpaRepository);
	}

	public ResponseDTO saveRecipient(RecipientDTOO recipientDTOO , String username ) throws Exception {
		ResponseDTO response=  new ResponseDTO();
			UserDTO userDTO = userService.findByUsername(username);
		Recipient recipient =modelMapper.map(recipientDTOO, Recipient.class);
		User user= this.modelMapper.map(userDTO, User.class);
		recipient.setUser(user);
		recipient=save(recipient);
		response.setBody("Recipient Save Succesfully!");
		response.setStatus("Success");
		return response; 

	}
	
	public RecipientDTOO findRecipientByName(String recipientName) {
		
		Recipient recipient = recipientRepository.findByName(recipientName);
		System.out.println("Recipient "+recipient);
		if (recipient == null) {
			System.out.println("Recipient Not found ");
		}
			return modelMapper.map(recipient, RecipientDTOO.class);
		}
	
public ResponseDTO deleteRecipientByName(String recipientName) {
		ResponseDTO response=  new ResponseDTO();
		Recipient recipient = recipientRepository.findByName(recipientName);
		if (recipient == null) {
			System.out.println("Recipient Not found ");
			response.setBody("Recipient Not found");
			response.setStatus("Failed");
			return response;
		}
			recipientRepository.deleteByName(recipientName);
			response.setBody("Recipient Deleted Succesfully!!");
			response.setStatus("Sucess");
			return response;
		}

public List<RecipientDTOO> getRecipientList(String username){
	UserDTO userDTO= userService.findByUsername(username);
	List<RecipientDTOO> recipientList=	new ArrayList<RecipientDTOO>(userDTO.getRecipientList());
  return recipientList;
}

	}


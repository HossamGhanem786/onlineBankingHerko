package com.onlineBank.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.onlineBank.domain.dto.RecipientDTOO;
import com.onlineBank.domain.dto.ResponseDTO;
import com.onlineBank.domain.model.Recipient;

public interface RecipientService {

	ResponseDTO saveRecipient(RecipientDTOO recipientDTOO , String Username ) throws Exception;
	RecipientDTOO findRecipientByName(String recipientName);
	 ResponseDTO deleteRecipientByName(String recipientName);
		List<RecipientDTOO> getRecipientList(String username);


}

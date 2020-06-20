package com.onlineBank.service;

import com.onlineBank.domain.dto.PrimaryAccountDTO;

public interface PrimaryAccountService {
	PrimaryAccountDTO savePrimaryAccount(PrimaryAccountDTO primaryAccountDTO) throws Exception;

}

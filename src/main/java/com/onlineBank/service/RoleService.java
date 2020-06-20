package com.onlineBank.service;

import java.util.List;

import com.onlineBank.domain.dto.RoleDTO;

public interface RoleService {
	RoleDTO saveRole(RoleDTO roleDTO) throws Exception;
	List<RoleDTO> viewRoles() throws Exception ;
}

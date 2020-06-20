package com.onlineBank.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineBank.domain.dto.RoleDTO;
import com.onlineBank.domain.model.Role;
import com.onlineBank.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends BaseAbstractService<Role> implements RoleService {

	public RoleServiceImpl(JpaRepository<Role, Long> jpaRepository) {
		super(jpaRepository);
	}

	public RoleDTO saveRole(RoleDTO roleDTO) throws Exception {
		Role role = this.save(modelMapper.map(roleDTO, Role.class));
		return modelMapper.map(role, RoleDTO.class);
	}

	public List<RoleDTO> viewRoles() throws Exception {
		return this.findAll().stream().map(role -> modelMapper.map(role, RoleDTO.class)).collect(Collectors.toList());
	}

}

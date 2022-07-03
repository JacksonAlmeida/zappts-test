package com.sunflower.zappts.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sunflower.zappts.dto.RoleDTO;
import com.sunflower.zappts.entities.Role;
import com.sunflower.zappts.repositories.RoleRepository;
import com.sunflower.zappts.services.exceptions.DatabaseException;
import com.sunflower.zappts.services.exceptions.ResourceNotFoundException;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role insert(Role role) {
		return roleRepository.save(role);
	}

	public List<RoleDTO> findAll() {
		List<Role> obj = roleRepository.findAll();
		return obj.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
	}

	public RoleDTO findById(long id) {
		Optional<Role> obj = roleRepository.findById(id);
		if (obj.isPresent()) {
			return obj.stream().map(x -> new RoleDTO(x)).findAny().get();
		} else {
			Optional<RoleDTO> dto = Optional.empty();
			return dto.orElseThrow(() -> new ResourceNotFoundException(id));
		}
	}

	public void delete(long id) {
		try {
			roleRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Role update(long id, Role obj) {
		try {
			Role entity = roleRepository.getOne(id);
			updateData(entity, obj);
			return roleRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Role entity, Role obj) {
		entity.setRoleName(obj.getRoleName());
	}
}
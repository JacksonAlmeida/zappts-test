package com.sunflower.zappts.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.sunflower.zappts.entities.Player;

public class PlayerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String password;
	private Set<RoleDTO> rolesDTO = new HashSet<>();

	public PlayerDTO() {
	}

	public PlayerDTO(long id, String name, Set<RoleDTO> rolesDTO) {
		this.id = id;
		this.name = name;
		this.rolesDTO = rolesDTO;
	}

	public PlayerDTO(Player x) {
		id = x.getId();
		name = x.getName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setRolesDTO(Set<RoleDTO> rolesDTO) {
		this.rolesDTO = rolesDTO;
	}
}

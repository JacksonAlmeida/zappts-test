package com.sunflower.zappts.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.sunflower.zappts.entities.Role;

public class RoleDTO {

	private long id;
	private String roleName;

	public RoleDTO() {

	}

	public RoleDTO(String roleName) {
		this.roleName = roleName;
	}

	public RoleDTO(Role obj) {
		id = obj.getId();
		roleName = obj.getRoleName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}

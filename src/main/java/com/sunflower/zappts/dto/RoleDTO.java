package com.sunflower.zappts.dto;

import java.io.Serializable;

import com.sunflower.zappts.entities.Role;

public class RoleDTO implements Serializable{

	private static final long serialVersionUID = 1L;
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

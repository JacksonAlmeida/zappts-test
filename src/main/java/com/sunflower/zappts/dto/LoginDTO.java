package com.sunflower.zappts.dto;

public class LoginDTO {

	private String name;
	private String password;
	
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginDTO(String name, String password) {
		this.name = name;
		this.password = password;
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

	public void setPassword(String password) {
		this.password = password;
	}
	
}

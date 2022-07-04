package com.sunflower.zappts.dto;

import java.io.Serializable;

import com.sunflower.zappts.entities.Player;

public class PlayerLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String token;

	public PlayerLogin() {
	}

	public PlayerLogin(Player p1) {
		p1.setName(name);
		p1.setPassword(password);
	}

	public PlayerLogin(Player p1, String token) {
		p1.setName(name);
		p1.setPassword(password);
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

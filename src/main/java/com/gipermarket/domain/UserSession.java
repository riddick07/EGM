package com.gipermarket.domain;

import java.io.Serializable;

import com.gipermarket.dao.enums.RolesEnum;

/**
 * USER SESSION parameters
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class UserSession implements Serializable {
	private static final long serialVersionUID = 7788535791309788330L;
	private String login;
	private String password;
	private RolesEnum role;

	public UserSession() {
	}

	public UserSession(String login, String password, RolesEnum role) {
		super();
		this.role = role;
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RolesEnum getRole() {
		return role;
	}

	public void setRole(RolesEnum role) {
		this.role = role;
	}
}

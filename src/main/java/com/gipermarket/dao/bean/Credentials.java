package com.gipermarket.dao.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gipermarket.dao.enums.RolesEnum;

@Entity
@Table(name = "Credentials", catalog = "egipermarket")
public class Credentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4138051911273250798L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "userId")
	private Long userId;
	
	@Column(name = "role")
	private RolesEnum role;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	public Credentials(Long userId, String login, String password) {
		super();
		this.userId = userId;
		this.login = login;
		this.password = password;
	}

	public Credentials() {
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

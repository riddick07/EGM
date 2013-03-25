package com.gipermarket.dao.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Credentials", catalog = "egipermarket")
public class Credentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4138051911273250798L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name")
	private Long userId;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}

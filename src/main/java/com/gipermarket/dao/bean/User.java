package com.gipermarket.dao.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User bean
 * 
 * @author DO\dmitry.karpenko
 * 
 */
@Entity
@Table(name = "User", catalog = "egipermarket")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2362458665207591424L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;
	
	@OneToOne
	private Credentials credentials;

	@Column(name = "mail")
	private String mail;

	@Column(name = "phone")
	private String phone;

	@Column(name = "registrationDate")
	private Date registrationDate;

	public User() {
		
	}
	
	public User(String name, String surname, String mail, String phone) {
		super();
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.phone = phone;
		this.registrationDate = new Date(System.currentTimeMillis());
	}
	
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Long getId() {
		return id;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate() {
		this.registrationDate = new Date(System.currentTimeMillis());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}

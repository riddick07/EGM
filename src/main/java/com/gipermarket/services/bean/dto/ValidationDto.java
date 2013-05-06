package com.gipermarket.services.bean.dto;

import com.gipermarket.dao.enums.RolesEnum;

/**
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class ValidationDto {

	private Boolean isValid;
	private RolesEnum role;
	private String message;

	public RolesEnum getRole() {
		return role;
	}

	public void setRole(RolesEnum role) {
		this.role = role;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

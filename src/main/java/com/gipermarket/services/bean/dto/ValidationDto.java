package com.gipermarket.services.bean.dto;

/**
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class ValidationDto {

	private Boolean isValid;
	private String message;

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

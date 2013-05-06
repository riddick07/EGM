package com.gipermarket.wordpress.domain.dto;

/**
 * WP site credentials and url
 * 
 * @author DO\dmitry.karpenko
 * 
 */
public class WPSiteDto {

	private String username;
	private String password;
	private String xmlRpcUrl;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getXmlRpcUrl() {
		return xmlRpcUrl;
	}

	public void setXmlRpcUrl(String xmlRpcUrl) {
		this.xmlRpcUrl = xmlRpcUrl;
	}
}

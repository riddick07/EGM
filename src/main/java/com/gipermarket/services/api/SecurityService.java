package com.gipermarket.services.api;

public interface SecurityService {

	public Boolean validateCredentials(String login, String password);
	
}

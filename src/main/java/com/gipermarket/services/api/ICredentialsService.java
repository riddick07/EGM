package com.gipermarket.services.api;

import com.gipermarket.services.bean.dto.ValidationDto;

/**
 * 
 * @author DO\dmitry.karpenko
 *
 */
public interface ICredentialsService {
	
	public ValidationDto validateCredentials(String login, String password);
	
}
